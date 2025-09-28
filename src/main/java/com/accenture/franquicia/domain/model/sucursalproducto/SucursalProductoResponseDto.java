package com.accenture.franquicia.domain.model.sucursalproducto;

import com.accenture.franquicia.domain.model.producto.ProductoDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalDto;

import java.util.List;

public record SucursalProductoResponseDto(SucursalDto sucursalDto, List<ProductoDto> lista) {


}
