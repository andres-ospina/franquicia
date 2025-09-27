package com.accenture.franquicia.infrastructure.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("sucursal_producto")
public class SucursalProductoEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("id_sucursal")
    private Long idSucursal;

    @Column("id_producto")
    private Long idProducto;


}
