package com.accenture.franquicia.domain.convert;

import com.accenture.franquicia.domain.model.producto.ProductoDto;
import com.accenture.franquicia.domain.model.producto.ProductoResponseDto;
import com.accenture.franquicia.domain.model.producto.ProductoSaveDto;
import com.accenture.franquicia.infrastructure.entity.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoConvert {
    public ProductoEntity convertEntity(ProductoSaveDto dto) {
        return new ProductoEntity(null, dto.nombre(),dto.cantidadStock());

    }

    public ProductoEntity convertEntity(ProductoDto dto) {
        return new ProductoEntity(dto.getId(), dto.getNombre(),dto.getCantidadstock());

    }

    public ProductoDto convertDto(ProductoEntity entity) {
        return new ProductoDto(entity.getId(), entity.getNombre(),entity.getCantidadStock());
    }

    public ProductoResponseDto convertResponseDto(ProductoEntity entity){
        return new ProductoResponseDto(entity.getId(), entity.getNombre(), entity.getCantidadStock());
    }
}
