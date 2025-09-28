package com.accenture.franquicia.domain.port.in;

import com.accenture.franquicia.domain.model.sucursal.SucursalNombreUpdateRequestDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalResponseDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoRequestDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoResponseDto;
import reactor.core.publisher.Mono;

public interface SucursalIn {

    /**
     * Se utiliza para almacenar un producto en una sucursal
     * @param request informacion de la sucursal y el producto a ingresar
     * @return la informacion de la sucursal con la lista de productos que tiene asociado
     */
    Mono<SucursalProductoResponseDto> sucursalProductoSave(Mono<SucursalProductoRequestDto> request);

    Mono<SucursalResponseDto> nombreUpdate(Long id, Mono<SucursalNombreUpdateRequestDto> request);

}
