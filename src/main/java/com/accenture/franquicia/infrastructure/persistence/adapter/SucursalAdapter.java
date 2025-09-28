package com.accenture.franquicia.infrastructure.persistence.adapter;

import com.accenture.franquicia.domain.convert.SucursalConvert;
import com.accenture.franquicia.domain.model.sucursal.SucursalDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalResponseDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalSaveDto;
import com.accenture.franquicia.domain.port.out.SucursalPort;
import com.accenture.franquicia.infrastructure.entity.SucursalEntity;
import com.accenture.franquicia.infrastructure.persistence.repository.SucursalRepository;
import com.accenture.franquicia.util.constant.ConstantMessage;
import com.accenture.franquicia.util.controller.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class SucursalAdapter implements SucursalPort {

    private final SucursalRepository repository;
    private final SucursalConvert convert;

    @Override
    @Transactional
    public Mono<SucursalDto> save(Mono<SucursalSaveDto> sucursalSaveDtoMono) {
        return sucursalSaveDtoMono.map(convert::convertEntity).flatMap(entity ->{
            Mono<Boolean> repeatName = repository.repeatName(entity.getNombre()).hasElement();
            return repeatName.flatMap( exitName ->{
                if(Boolean.TRUE.equals(exitName)){
                    return Mono.error(new ValidationException(ConstantMessage.EXIT_SUCURSAL));
                }else{
                    return repository.save(entity);
                }
            });
        }).map(convert::convertDto);
    }

    @Override
    public Mono<SucursalDto> findById(Long id) {
        Mono<SucursalEntity> entity = repository.findById(id);
        return entity.map(convert::convertDto);
    }

    @Override
    public Mono<SucursalResponseDto> update(Mono<SucursalDto> sucursal) {
        return sucursal.map(convert::convertEntity).map(repository::save).flatMap(entity -> entity.map(convert::convertResponseDto));
    }
}
