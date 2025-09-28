package com.accenture.franquicia.domain.convert;

import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalSaveDto;
import com.accenture.franquicia.infrastructure.entity.FranquiciaSucursalEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class FranquiciaSucursalConvert {
    public FranquiciaSucursalEntity convertSaveEntity(FranquiciaSucursalSaveDto dto) {
        return new FranquiciaSucursalEntity(null, dto.idFranquicia(), dto.idSucursal());

    }

    public FranquiciaSucursalDto convertEntity(FranquiciaSucursalEntity entity) {
        return new FranquiciaSucursalDto(entity.getId(),
                entity.getIdFranquicia(),
                entity.getIdSucursal());
    }
}
