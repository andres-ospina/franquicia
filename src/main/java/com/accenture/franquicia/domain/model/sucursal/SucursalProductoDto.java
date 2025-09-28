package com.accenture.franquicia.domain.model.sucursal;

import com.accenture.franquicia.domain.model.producto.ProductoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalProductoDto {

    private Long id;
    private String nombre;
    private ProductoDto producto;
}
