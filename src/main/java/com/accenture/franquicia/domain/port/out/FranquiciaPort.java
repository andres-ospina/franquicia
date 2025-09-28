package com.accenture.franquicia.domain.port.out;

import com.accenture.franquicia.domain.model.franquicia.FranquiciaDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaResponseDto;
import reactor.core.publisher.Mono;

public interface FranquiciaPort {

    Mono<FranquiciaResponseDto> save(Mono<FranquiciaDto> dto);
    Mono<FranquiciaDto> findById(Long id);
    Mono<FranquiciaResponseDto>  update(Mono<FranquiciaDto> franquiciaDto);
}
