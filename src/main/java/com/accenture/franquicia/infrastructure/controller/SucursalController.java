package com.accenture.franquicia.infrastructure.controller;

import com.accenture.franquicia.domain.model.producto.ProductoRequestNombretUpdateDto;
import com.accenture.franquicia.domain.model.producto.ProductoResponseDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalNombreUpdateRequestDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalResponseDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoRequestDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoResponseDto;
import com.accenture.franquicia.domain.port.in.SucursalIn;
import com.accenture.franquicia.util.constant.ConstantMessage;
import com.accenture.franquicia.util.controller.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sucursal")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalIn sucursalIn;

    @PostMapping("/sucursal-producto")
    public Mono<GeneralResponse<SucursalProductoResponseDto>> sucursalProductoSave(@RequestBody SucursalProductoRequestDto sucursalProductoRequestDto){
        Mono<SucursalProductoRequestDto> request = Mono.just(sucursalProductoRequestDto);
        return sucursalIn.sucursalProductoSave(request)
                .map(sucursalProductoResponseDto -> new GeneralResponse<SucursalProductoResponseDto>(true, HttpStatus.OK.value(), ConstantMessage.SUCCEFULLY_STORED, sucursalProductoResponseDto));

    }

    @PutMapping("/nombre/{id}")
    public Mono<GeneralResponse<SucursalResponseDto>> nombreUpdate(@PathVariable("id") String id, @RequestBody SucursalNombreUpdateRequestDto sucursalNombreUpdateRequestDto){
        Long idSucursal = Long.parseLong(id);
        Mono<SucursalNombreUpdateRequestDto> request = Mono.just(sucursalNombreUpdateRequestDto);
        return sucursalIn.nombreUpdate(idSucursal,request)
                .map(sucursalResponseDto -> new GeneralResponse<SucursalResponseDto>(true, HttpStatus.OK.value(), ConstantMessage.SUCCEFULLY_STORED, sucursalResponseDto));
    }

}
