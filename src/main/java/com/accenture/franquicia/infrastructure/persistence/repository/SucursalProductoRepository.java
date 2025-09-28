package com.accenture.franquicia.infrastructure.persistence.repository;


import com.accenture.franquicia.domain.model.producto.ProductoDto;
import com.accenture.franquicia.infrastructure.entity.SucursalProductoEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SucursalProductoRepository extends ReactiveCrudRepository<SucursalProductoEntity, Long> {

    @Query("SELECT " +
            "p.id as id, " +
            "p.nombre as nombre, " +
            "p.cantidad_stock as cantidadstock " +
            "FROM sucursal_producto sp inner join producto p on sp.id_producto  = p.id " +
            "WHERE sp.id_sucursal =:idSucursal")
    Flux<ProductoDto> findAllByIdSucursal(Long idSucursal);


    @Query("SELECT * FROM sucursal_producto WHERE id_sucursal =:idSucursal AND id_producto =:idProducto")
    Mono<SucursalProductoEntity> findbyIdSucursalAndIdProducto(Long idSucursal, Long idProducto);

    @Query("SELECT " +
            "p.id as id, " +
            "p.nombre as nombre, " +
            "p.cantidad_stock as cantidadstock " +
            "FROM sucursal_producto sp LEFT JOIN  producto p ON sp.id_producto  = p.id " +
            "WHERE sp.id_sucursal =:idSucursal " +
            "ORDER BY p.cantidad_stock DESC " +
            "LIMIT 1")
    Mono<ProductoDto> findByIdSucursalMaxStockProducto(Long idSucursal);
}
