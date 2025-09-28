package com.accenture.franquicia.domain.port.out;

import com.accenture.franquicia.domain.model.producto.ProductoDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoResponseDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoSaveDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SucursalProductoPort {

    Mono<SucursalProductoDto> save (Mono<SucursalProductoSaveDto> productoSaveDtoMono);
    Mono<SucursalProductoResponseDto> findAllByIdSucursal(Long id);
    Mono<Void> delete(List<Long> ids);
    Mono<SucursalProductoDto> findbyIdSucursalAndIdProducto(Long idSucursal, Long idProducto);
    Mono<ProductoDto> findByIdSucursalMaxStockProducto(Long idSucursal);
}
