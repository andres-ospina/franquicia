package com.accenture.franquicia.domain.model.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

    private Long id;
    private String nombre;
    private int cantidadstock;

}
