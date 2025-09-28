package com.accenture.franquicia.infrastructure.persistence.repository;

import com.accenture.franquicia.infrastructure.entity.ProductoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<ProductoEntity, Long> {
}
