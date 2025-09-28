package com.accenture.franquicia.domain.port.out;

import com.accenture.franquicia.domain.model.producto.ProductoDto;
import com.accenture.franquicia.domain.model.producto.ProductoResponseDto;
import com.accenture.franquicia.domain.model.producto.ProductoSaveDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductoPort {

    Mono<ProductoDto> save(Mono<ProductoSaveDto> productoSaveDto);
    Mono<ProductoDto> findById(Long id);
    Mono<Void> delete(List<Long> ids);
    Mono<ProductoResponseDto> update(Mono<ProductoDto> producto);

}
