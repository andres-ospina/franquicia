package com.accenture.franquicia.infrastructure.persistence.adapter;

import com.accenture.franquicia.domain.convert.FranquiciaConvert;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaResponseDto;
import com.accenture.franquicia.domain.port.out.FranquiciaPort;
import com.accenture.franquicia.infrastructure.entity.FranquiciaEntity;
import com.accenture.franquicia.infrastructure.persistence.repository.FranquiciaRepository;
import com.accenture.franquicia.util.constant.ConstantMessage;
import com.accenture.franquicia.util.controller.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
@Slf4j
public class FranquiciaAdapter implements FranquiciaPort {

    private final FranquiciaRepository repository;
    private final FranquiciaConvert convert;

    @Override
    @Transactional
    public Mono<FranquiciaResponseDto> save(Mono<FranquiciaDto> dto) {

        return dto.map(convert::convertEntity).flatMap( entity -> {
            Mono<Boolean> repeatName = repository.repeatName(entity.getNombre()).hasElement();
             return repeatName.flatMap( exitName ->{
                if(Boolean.TRUE.equals(exitName)){
                    return Mono.error(new ValidationException(ConstantMessage.EXIT_FRANQUICIA));
                }else{
                    return repository.save(entity);
                }
            });
        }).map(convert::convertReponseDto);
    }

    @Override
    public Mono<FranquiciaDto> findById(Long id) {
        Mono<FranquiciaEntity> entity = repository.findById(id);
        return entity.map(convert::convertDto);
    }

    @Override
    public Mono<FranquiciaResponseDto> update(Mono<FranquiciaDto> franquicia) {
        return franquicia.map(convert::convertEntity).map(repository::save).flatMap(entity -> entity.map(convert::convertResponseDto));
    }
}
