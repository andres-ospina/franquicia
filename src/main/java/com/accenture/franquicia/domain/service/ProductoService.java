package com.accenture.franquicia.domain.service;

import com.accenture.franquicia.domain.model.producto.*;
import com.accenture.franquicia.domain.port.in.ProductoIn;
import com.accenture.franquicia.domain.port.out.ProductoPort;
import com.accenture.franquicia.domain.port.out.SucursalProductoPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductoService implements ProductoIn {

    private final ProductoPort productoPort;
    private final SucursalProductoPort sucursalProductoPort;

    @Override
    public Mono<ProductoResponseDto> stockUpdate(Mono<ProductoRequestStocktUpdateDto> request) {

        return request.flatMap(dto -> {
           return productoPort.findById(dto.id()).flatMap(productoDto -> {
                productoDto.setCantidadstock(dto.cantidadstock());
                return productoPort.update(Mono.just(productoDto));
            });

        });

    }

    @Override
    public Mono<Void> delete(Mono<ProductoRequestSucursalDeleteDto> productoRequestSucursalDeleteDto) {

        return productoRequestSucursalDeleteDto.flatMap(request -> sucursalProductoPort.findbyIdSucursalAndIdProducto(request.idSucursal(), request.idProducto()).map(spdto -> {
               List<Long> ids = new ArrayList<>();
               ids.add(spdto.id());

               List<Long> idsProducto = new ArrayList<>();
               idsProducto.add(spdto.id());
               Mono<Void> p1 = sucursalProductoPort.delete(ids);
               Mono<Void> p2 = productoPort.delete(idsProducto);
               return p1.then(p2);
           })).then();

    }

    @Override
    public Mono<ProductoResponseDto> nombreUpdate(Mono<ProductoRequestNombretUpdateDto> request) {
        return request.flatMap(dto -> {
            return productoPort.findById(dto.id()).flatMap(productoDto -> {
                productoDto.setNombre(dto.nombre());
                return productoPort.update(Mono.just(productoDto));
            });

        });
    }
}
