package com.accenture.franquicia.infrastructure.persistence.adapter;

import com.accenture.franquicia.domain.convert.FranquiciaSucursalConvert;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalResponseDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalSaveDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalDto;
import com.accenture.franquicia.domain.port.out.FranquiciaSucursalPort;
import com.accenture.franquicia.infrastructure.entity.FranquiciaEntity;
import com.accenture.franquicia.infrastructure.persistence.repository.FranquiciaRepository;
import com.accenture.franquicia.infrastructure.persistence.repository.FranquiciaSucursalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class FranquiciaSucursalAdapter implements FranquiciaSucursalPort {

    private final FranquiciaSucursalRepository repository;
    private final FranquiciaSucursalConvert convert;
    private final FranquiciaRepository franquiciaRepository;

    @Override
    @Transactional
    public Mono<FranquiciaSucursalDto> save(Mono<FranquiciaSucursalSaveDto> franquiciaSucursalSaveDtoMono) {
        return franquiciaSucursalSaveDtoMono
                .map(convert::convertSaveEntity)
                .map(repository::save).flatMap(entity -> entity.map(convert::convertEntity));

    }

    @Override
    public Mono<FranquiciaSucursalResponseDto> findAllByIdFranquicia(Long idfranquicia) {
        Flux<SucursalDto> list = repository.findAllByIdFranquicia(idfranquicia);
        Mono<FranquiciaEntity> franquiciaEntity = franquiciaRepository.findById(idfranquicia);
        return list.collectList().zipWith(franquiciaEntity).flatMap(tuple -> {
            FranquiciaEntity franquicia = tuple.getT2();
            List<SucursalDto> listDto = tuple.getT1();
            return Mono.just(new FranquiciaSucursalResponseDto(new FranquiciaDto(franquicia.getId(), franquicia.getNombre()),listDto));
        });
    }

    @Override
    public Flux<SucursalDto> findAllSurcursalByIdFranquicia(Long idFranquicia) {
        return repository.findAllByIdFranquicia(idFranquicia);
    }
}
