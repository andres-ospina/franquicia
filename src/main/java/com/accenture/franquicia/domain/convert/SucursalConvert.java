package com.accenture.franquicia.domain.convert;

import com.accenture.franquicia.domain.model.sucursal.SucursalDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalResponseDto;
import com.accenture.franquicia.domain.model.sucursal.SucursalSaveDto;
import com.accenture.franquicia.infrastructure.entity.SucursalEntity;
import org.springframework.stereotype.Component;

@Component
public class SucursalConvert {
    public SucursalEntity convertEntity(SucursalSaveDto sucursalSaveDto) {
        return new SucursalEntity(null, sucursalSaveDto.nombre());
    }

    public SucursalEntity convertEntity(SucursalDto dto) {
        return new SucursalEntity(dto.getId(), dto.getNombre());
    }

    public SucursalDto convertDto(SucursalEntity entity) {
        return new SucursalDto(entity.getId(), entity.getNombre());
    }

    public SucursalResponseDto convertResponseDto(SucursalEntity entity) {
        return new SucursalResponseDto(entity.getId(), entity.getNombre());
    }
}
