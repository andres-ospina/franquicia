package com.accenture.franquicia.domain.port.out;

import com.accenture.franquicia.domain.model.sucursal.SucursalDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalResponseDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalSaveDto;
import reactor.core.publisher.Mono;

public interface SucursalPort {

    Mono<SucursalDto> save(Mono<SucursalSaveDto> sucursalSaveDtoMono);
    Mono<SucursalDto> findById(Long id);
    Mono<SucursalResponseDto> update(Mono<SucursalDto> sucursalDto);
}
