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
@Table("sucursal")
public class SucursalEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("nombre")
    private String nombre;
}
