package com.accenture.franquicia.domain.model.franquiciasucursal;

import com.accenture.franquicia.domain.model.franquicia.FranquiciaDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalDto;

import java.util.List;

public record FranquiciaSucursalResponseDto(FranquiciaDto franquiciaDto, List<SucursalDto> listaSucursal) {
}
