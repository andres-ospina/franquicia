package com.accenture.franquicia.domain.port.in;

import com.accenture.franquicia.domain.model.producto.ProductoRequestNombretUpdateDto;
import com.accenture.franquicia.domain.model.producto.ProductoRequestStocktUpdateDto;
import com.accenture.franquicia.domain.model.producto.ProductoRequestSucursalDeleteDto;
import com.accenture.franquicia.domain.model.producto.ProductoResponseDto;
import reactor.core.publisher.Mono;

public interface ProductoIn {

    /**
     * Se utiliza para actualizar el stock del producto
     * @param productoRequesUpdateStocktDto la informacion del producto y el stock a modificar
     * @return la informacion del producto modificado
     */
    Mono<ProductoResponseDto> stockUpdate(Mono<ProductoRequestStocktUpdateDto> productoRequesUpdateStocktDto);

    /**
     * Se utiliza para eliminar un producto de una sucursal
     * @param productoRequestSucursalDeleteDto informacion de la sucursal y del producto
     * @return un mensaje de eliminado
     */
    Mono<Void> delete(Mono<ProductoRequestSucursalDeleteDto> productoRequestSucursalDeleteDto);

    /**
     * Se utiliza para actualizar el nombre del producto enviado
     * @param productoRequestNombretUpdateDto informacion del producto y el nombre
     * @return informacion del producto modificado
     */
    Mono<ProductoResponseDto> nombreUpdate(Mono<ProductoRequestNombretUpdateDto> productoRequestNombretUpdateDto);

}
