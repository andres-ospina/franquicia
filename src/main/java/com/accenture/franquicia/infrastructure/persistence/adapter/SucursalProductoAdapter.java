package com.accenture.franquicia.infrastructure.persistence.adapter;


import com.accenture.franquicia.domain.convert.SucursalProductoConvert;
import com.accenture.franquicia.domain.model.producto.ProductoDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoResponseDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoSaveDto;
import com.accenture.franquicia.domain.port.out.SucursalProductoPort;
import com.accenture.franquicia.infrastructure.entity.SucursalProductoEntity;
import com.accenture.franquicia.infrastructure.persistence.repository.SucursalProductoRepository;
import com.accenture.franquicia.infrastructure.persistence.repository.SucursalRepository;
import com.accenture.franquicia.util.constant.ConstantMessage;
import com.accenture.franquicia.util.controller.exception.DeleteException;
import io.r2dbc.spi.R2dbcDataIntegrityViolationException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@AllArgsConstructor
public class SucursalProductoAdapter implements SucursalProductoPort {

    private final SucursalProductoRepository repository;
    private final SucursalProductoConvert convert;
    private final SucursalRepository sucursalRepository;

    @Override
    public Mono<SucursalProductoDto> save(Mono<SucursalProductoSaveDto> productoSaveDto) {
        return productoSaveDto.map(convert::convertEntity).map(repository::save).flatMap(entity -> entity.map(convert::convertDto));

    }

    @Override
    public Mono<SucursalProductoResponseDto> findAllByIdSucursal(Long idSucursal) {
        return repository.findAllByIdSucursal(idSucursal).collectList().zipWith(sucursalRepository.findById(idSucursal)).flatMap(tuple -> {
             return Mono.just(new SucursalProductoResponseDto(new SucursalDto(tuple.getT2().getId(), tuple.getT2().getNombre()), tuple.getT1()));
        });

    }

    @Override
    @Transactional
    public Mono<Void> delete(List<Long> ids) {
        return repository.deleteAllById(ids)
                .onErrorResume(throwable -> {
                    if (throwable instanceof DataIntegrityViolationException
                            || throwable instanceof R2dbcDataIntegrityViolationException) {
                        return Mono.error(new DeleteException(ConstantMessage.CANNOT_DELETE_RECORD));
                    }
                    return Mono.error(throwable);
                }).then();
    }

    @Override
    public Mono<SucursalProductoDto> findbyIdSucursalAndIdProducto(Long idSucursal, Long idProducto) {
        Mono<SucursalProductoEntity> entity = repository.findbyIdSucursalAndIdProducto(idSucursal,idProducto);
        return entity.map(convert::convertDto);
    }

    @Override
    public Mono<ProductoDto> findByIdSucursalMaxStockProducto(Long idSucursal) {
        Mono<ProductoDto>  prod = repository.findByIdSucursalMaxStockProducto(idSucursal);
        return null;
    }


}
