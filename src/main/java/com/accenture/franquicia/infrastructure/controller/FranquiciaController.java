package com.accenture.franquicia.infrastructure.controller;

import com.accenture.franquicia.domain.model.franquicia.FranquiciaNombreUpdateRequestDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaRequestDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaResponseDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaSucursalMaxStockPorProductoResponseDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalRequestDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalResponseDto;
import com.accenture.franquicia.domain.model.producto.ProductoRequestNombretUpdateDto;
import com.accenture.franquicia.domain.model.producto.ProductoResponseDto;
import com.accenture.franquicia.domain.port.in.FranquiciaIn;
import com.accenture.franquicia.util.constant.ConstantMessage;
import com.accenture.franquicia.util.controller.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franquicia")
@RequiredArgsConstructor
public class FranquiciaController {

    private final FranquiciaIn franquiciaIn;

   @PostMapping
    public Mono<GeneralResponse<FranquiciaResponseDto>> save(@RequestBody FranquiciaRequestDto franquiciaRequestDto){
       Mono<FranquiciaRequestDto> request = Mono.just(franquiciaRequestDto);
       return franquiciaIn.save(request)
               .map(franquiciaResponseDto -> new GeneralResponse<FranquiciaResponseDto>(true, HttpStatus.OK.value(), ConstantMessage.SUCCEFULLY_STORED, franquiciaResponseDto));
   }

   @PostMapping("/franquicia-sucursal")
    public Mono<GeneralResponse<FranquiciaSucursalResponseDto>> franquiciaSucursalSave(@RequestBody FranquiciaSucursalRequestDto franquiciaSucursalRequestDto){
       Mono<FranquiciaSucursalRequestDto> request = Mono.just(franquiciaSucursalRequestDto);
       return franquiciaIn.franquiciaSucursalSave(request)
               .map(franquiciaSucursalResponseDto -> new GeneralResponse<FranquiciaSucursalResponseDto>(true, HttpStatus.OK.value(), ConstantMessage.SUCCEFULLY_STORED, franquiciaSucursalResponseDto));

   }

   @GetMapping("/stock-producto")
   public Mono<GeneralResponse<FranquiciaSucursalMaxStockPorProductoResponseDto>> maxStockPorProducto(@PathVariable("id") String id){
       Long idFranquicia = Long.parseLong(id);
       return franquiciaIn.maxStockPorProducto(idFranquicia)
               .map(franquiciaSucursalMaxStockPorProductoResponseDto -> new GeneralResponse<FranquiciaSucursalMaxStockPorProductoResponseDto>(true, HttpStatus.OK.value(), ConstantMessage.SUCCEFULLY_STORED, franquiciaSucursalMaxStockPorProductoResponseDto));

   }


    @PutMapping("/nombre/{id}")
    public Mono<GeneralResponse<FranquiciaResponseDto>> nombreUpdate(@PathVariable("id") String id, @RequestBody FranquiciaNombreUpdateRequestDto franquiciaNombreUpdateRequestDto){
        Long idFranquicia = Long.parseLong(id);
        Mono<FranquiciaNombreUpdateRequestDto> request = Mono.just(franquiciaNombreUpdateRequestDto);
        return franquiciaIn.nombreUpdate(idFranquicia,request)
                .map(franquiciaResponseDto -> new GeneralResponse<FranquiciaResponseDto>(true, HttpStatus.OK.value(), ConstantMessage.SUCCEFULLY_STORED, franquiciaResponseDto));
    }


}
