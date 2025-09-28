package com.accenture.franquicia.infrastructure.controller;


import com.accenture.franquicia.domain.model.producto.ProductoRequestNombretUpdateDto;
import com.accenture.franquicia.domain.model.producto.ProductoRequestStocktUpdateDto;
import com.accenture.franquicia.domain.model.producto.ProductoRequestSucursalDeleteDto;
import com.accenture.franquicia.domain.model.producto.ProductoResponseDto;
import com.accenture.franquicia.domain.port.in.ProductoIn;
import com.accenture.franquicia.util.constant.ConstantMessage;
import com.accenture.franquicia.util.controller.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoIn productoIn;


    @DeleteMapping
    public Mono<GeneralResponse<String>> delete(@RequestBody ProductoRequestSucursalDeleteDto productoRequestSucursalDeleteDto) {
        Mono<ProductoRequestSucursalDeleteDto> request = Mono.just(productoRequestSucursalDeleteDto);
        return productoIn.delete(request).then(Mono.just(new GeneralResponse<String>(true, HttpStatus.OK.value(), ConstantMessage.SUCCESSFULLY_DELETED, null)));

    }

    @PutMapping("/stock/{id}")
    public Mono<GeneralResponse<ProductoResponseDto>> stockUpdate(@RequestBody ProductoRequestStocktUpdateDto productoRequesUpdateStocktDto){
        Mono<ProductoRequestStocktUpdateDto> request = Mono.just(productoRequesUpdateStocktDto);
        return productoIn.stockUpdate(request)
                .map(productoResponseDto -> new GeneralResponse<ProductoResponseDto>(true, HttpStatus.OK.value(), ConstantMessage.SUCCEFULLY_STORED, productoResponseDto));
    }

    @PutMapping("/nombre/{id}")
    public Mono<GeneralResponse<ProductoResponseDto>> nombreUpdate(@RequestBody ProductoRequestNombretUpdateDto productoRequestNombretUpdateDto){
        Mono<ProductoRequestNombretUpdateDto> request = Mono.just(productoRequestNombretUpdateDto);
        return productoIn.nombreUpdate(request)
                .map(productoResponseDto -> new GeneralResponse<ProductoResponseDto>(true, HttpStatus.OK.value(), ConstantMessage.SUCCEFULLY_STORED, productoResponseDto));
    }







}
