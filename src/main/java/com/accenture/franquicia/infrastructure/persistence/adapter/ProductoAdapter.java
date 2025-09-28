package com.accenture.franquicia.infrastructure.persistence.adapter;

import com.accenture.franquicia.domain.convert.ProductoConvert;
import com.accenture.franquicia.domain.model.producto.ProductoDto;
import com.accenture.franquicia.domain.model.producto.ProductoResponseDto;
import com.accenture.franquicia.domain.model.producto.ProductoSaveDto;
import com.accenture.franquicia.domain.port.out.ProductoPort;
import com.accenture.franquicia.infrastructure.entity.ProductoEntity;
import com.accenture.franquicia.infrastructure.persistence.repository.ProductoRepository;
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
public class ProductoAdapter implements ProductoPort {

    private final ProductoRepository repository;
    private final ProductoConvert convert;

    @Override
    public Mono<ProductoDto> save(Mono<ProductoSaveDto> productoSaveDto) {
       return productoSaveDto.map(convert::convertEntity).map(repository::save).flatMap(result -> result.map(convert::convertDto));

    }

    @Override
    public Mono<ProductoDto> findById(Long id) {
        Mono<ProductoEntity> entity = repository.findById(id);
        return entity.map(convert::convertDto);
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
    public Mono<ProductoResponseDto> update(Mono<ProductoDto> producto) {
        return producto.map(convert::convertEntity).map(repository::save).flatMap(entity -> entity.map(convert::convertResponseDto));

    }
}
