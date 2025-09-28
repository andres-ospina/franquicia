package com.accenture.franquicia.domain.port.out;

import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalResponseDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalSaveDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranquiciaSucursalPort {

    Mono<FranquiciaSucursalDto> save(Mono<FranquiciaSucursalSaveDto> franquiciaSucursalSaveDtoMono);

    Mono<FranquiciaSucursalResponseDto> findAllByIdFranquicia(Long idFranquicia);

    Flux<SucursalDto> findAllSurcursalByIdFranquicia(Long idFranquicia);

}
