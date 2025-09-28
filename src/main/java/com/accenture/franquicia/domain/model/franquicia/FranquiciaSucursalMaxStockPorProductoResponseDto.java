package com.accenture.franquicia.domain.model.franquicia;


import com.accenture.franquicia.domain.model.sucursal.SucursalProductoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FranquiciaSucursalMaxStockPorProductoResponseDto {

    private List<SucursalProductoDto> sucursal;
}
