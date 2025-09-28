package com.accenture.franquicia.infrastructure.persistence.repository;

import com.accenture.franquicia.infrastructure.entity.FranquiciaEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FranquiciaRepository extends ReactiveCrudRepository<FranquiciaEntity, Long> {

    @Query("SELECT * FROM franquicia  WHERE nombre =:nombre")
    Mono<FranquiciaEntity> repeatName(String nombre);
}
