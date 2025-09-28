package com.accenture.franquicia.infrastructure.persistence.repository;

import com.accenture.franquicia.infrastructure.entity.SucursalEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SucursalRepository extends ReactiveCrudRepository<SucursalEntity, Long> {

    @Query("SELECT * FROM sucursal  WHERE nombre =:nombre")
    Mono<SucursalEntity> repeatName(String nombre);
}
