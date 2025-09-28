package com.accenture.franquicia.domain.service;

import com.accenture.franquicia.domain.model.producto.ProductoDto;
import com.accenture.franquicia.domain.model.producto.ProductoSaveDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalNombreUpdateRequestDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalResponseDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoRequestDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoResponseDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoSaveDto;
import com.accenture.franquicia.domain.port.in.SucursalIn;
import com.accenture.franquicia.domain.port.out.ProductoPort;
import com.accenture.franquicia.domain.port.out.SucursalPort;
import com.accenture.franquicia.domain.port.out.SucursalProductoPort;
import com.accenture.franquicia.util.constant.ConstantMessage;
import com.accenture.franquicia.util.controller.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SucursalService implements SucursalIn {

    private final ProductoPort productoPort;
    private final SucursalPort sucurasalPort;
    private final SucursalProductoPort sucursalProductoPort;


    @Override
    public Mono<SucursalProductoResponseDto> sucursalProductoSave(Mono<SucursalProductoRequestDto> sucursalProductoRequestDto) {

        return sucursalProductoRequestDto.flatMap(request -> productoPort.save(Mono.just(new ProductoSaveDto(request.nombreProducto(), request.stock()))).zipWith(sucurasalPort.findById(request.idSucursal()).hasElement()).flatMap(tuple -> {
            ProductoDto productoDto = tuple.getT1();
            Boolean exitSucursal = tuple.getT2();
            if (exitSucursal) {
                return sucursalProductoPort.save(Mono.just(new SucursalProductoSaveDto(request.idSucursal(), productoDto.getId()))).flatMap(sucursalProductoDto -> sucursalProductoPort.findAllByIdSucursal(request.idSucursal()));
            } else {
                return Mono.error(new ValidationException(ConstantMessage.NO_EXIT_SUCURSAL));
            }

        }));

    }

    @Override
    public Mono<SucursalResponseDto> nombreUpdate(Long id, Mono<SucursalNombreUpdateRequestDto> request) {
       Mono<SucursalDto>  result = request.zipWith(sucurasalPort.findById(id)).flatMap(tuple -> {
            SucursalDto sucursalDto = tuple.getT2();
            SucursalNombreUpdateRequestDto dto = tuple.getT1();
            sucursalDto.setNombre(dto.nombre());
             return Mono.just(sucursalDto);

        });

       return sucurasalPort.update(result);


    }
}
