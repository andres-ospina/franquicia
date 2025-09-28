package com.accenture.franquicia.domain.convert;

import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoDto;
import com.accenture.franquicia.domain.model.sucursalproducto.SucursalProductoSaveDto;
import com.accenture.franquicia.infrastructure.entity.SucursalProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class SucursalProductoConvert {
    public SucursalProductoEntity convertEntity(SucursalProductoSaveDto dto) {
        return new SucursalProductoEntity(null, dto.idSucursal(), dto.idProducto());
    }

    public SucursalProductoDto convertDto(SucursalProductoEntity entity) {
        return new SucursalProductoDto(entity.getId(), entity.getIdSucursal(), entity.getIdProducto());
    }
}
