package com.accenture.franquicia.infrastructure.persistence.repository;

import com.accenture.franquicia.domain.model.sucursal.SucursalDto;
import com.accenture.franquicia.infrastructure.entity.FranquiciaSucursalEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FranquiciaSucursalRepository extends ReactiveCrudRepository<FranquiciaSucursalEntity, Long> {

    @Query("SELECT " +
            "s.id as id, " +
            "s.nombre as nombre " +
            "FROM franquicia_sucursal fs left join sucursal s on fs.id_sucursal = s.id " +
            "WHERE fs.id_franquicia =:idfranquicia ")
    Flux<SucursalDto> findAllByIdFranquicia(Long idfranquicia);

}
