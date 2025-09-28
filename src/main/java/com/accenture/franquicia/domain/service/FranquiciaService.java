package com.accenture.franquicia.domain.service;

import com.accenture.franquicia.domain.convert.FranquiciaConvert;
import com.accenture.franquicia.domain.model.franquicia.*;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalRequestDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalResponseDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalSaveDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalProductoDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalSaveDto;
import com.accenture.franquicia.domain.port.in.FranquiciaIn;
import com.accenture.franquicia.domain.port.out.FranquiciaPort;
import com.accenture.franquicia.domain.port.out.FranquiciaSucursalPort;
import com.accenture.franquicia.domain.port.out.SucursalPort;
import com.accenture.franquicia.domain.port.out.SucursalProductoPort;
import com.accenture.franquicia.util.constant.ConstantMessage;
import com.accenture.franquicia.util.controller.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public  class FranquiciaService implements FranquiciaIn {

    private final FranquiciaPort franquiciaPort;
    private final SucursalPort sucursalPort;
    private final FranquiciaConvert convert;
    private final FranquiciaSucursalPort franquiciaSucursalPort;
    private final SucursalProductoPort sucursalProductoPort;

    @Override
    public Mono<FranquiciaResponseDto> save(Mono<FranquiciaRequestDto> franquiciaRequestDto) {
        Mono<FranquiciaDto> dto = franquiciaRequestDto
                .map(convert::franquiciaSaveDto);
        return franquiciaPort.save(dto);

    }

    @Override
    public Mono<FranquiciaSucursalResponseDto> franquiciaSucursalSave(Mono<FranquiciaSucursalRequestDto> franquiciaSucursalRequestDtoMono) {

        return franquiciaSucursalRequestDtoMono.flatMap(request -> sucursalPort.save(Mono.just(new SucursalSaveDto(request.nombreSucursal()))).zipWith(franquiciaPort.findById(request.idfranquicia()).hasElement()).flatMap(tuple -> {
          SucursalDto sucursalDto = tuple.getT1();
          Boolean exitFranquicia = tuple.getT2();
          if(exitFranquicia){
              return franquiciaSucursalPort.save(Mono.just(new FranquiciaSucursalSaveDto(request.idfranquicia(), sucursalDto.getId()))).flatMap( franquiciaSucursalDto -> franquiciaSucursalPort.findAllByIdFranquicia(request.idfranquicia()));

          }else{
              return Mono.error(new ValidationException(ConstantMessage.NO_EXIT_FRANQUICIA));
          }

      }));

    }

    @Override
    public Mono<FranquiciaSucursalMaxStockPorProductoResponseDto> maxStockPorProducto(Long id) {

        return franquiciaSucursalPort.findAllSurcursalByIdFranquicia(id).flatMap(list ->
                sucursalProductoPort.findByIdSucursalMaxStockProducto(list.getId()).map(product -> new SucursalProductoDto(list.getId(),list.getNombre(),product)))
                .collectList().map(FranquiciaSucursalMaxStockPorProductoResponseDto::new);

    }

    @Override
    public Mono<FranquiciaResponseDto> nombreUpdate(Long id, Mono<FranquiciaNombreUpdateRequestDto> request) {
        Mono<FranquiciaDto> franquiciaDto = request.zipWith(franquiciaPort.findById(id)).map(tuple -> {
            FranquiciaDto dto = tuple.getT2();
            FranquiciaNombreUpdateRequestDto franquiciaNombreUpdateRequestDto = tuple.getT1();
            dto.setNombre(franquiciaNombreUpdateRequestDto.nombre());

            return dto;
        });

        return franquiciaPort.update(franquiciaDto);
    }




}
