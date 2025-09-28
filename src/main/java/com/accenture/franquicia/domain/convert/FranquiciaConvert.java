package com.accenture.franquicia.domain.convert;

import com.accenture.franquicia.domain.model.franquicia.FranquiciaDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaRequestDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaResponseDto;
import com.accenture.franquicia.infrastructure.entity.FranquiciaEntity;
import org.springframework.stereotype.Component;

@Component
public class FranquiciaConvert {

    public FranquiciaDto franquiciaSaveDto( FranquiciaRequestDto franquiciaRequestDto) {
        return new FranquiciaDto(null, franquiciaRequestDto.nombre())   ;
    }

    public FranquiciaEntity convertEntity(FranquiciaDto dto) {
        return new FranquiciaEntity(dto.getId(), dto.getNombre());
    }

    public FranquiciaResponseDto convertReponseDto(FranquiciaEntity entity) {
        return new FranquiciaResponseDto(entity.getId(), entity.getNombre());

    }

    public FranquiciaDto convertDto(FranquiciaEntity entity) {
        return new FranquiciaDto(entity.getId(), entity.getNombre());
    }

    public FranquiciaResponseDto convertResponseDto(FranquiciaEntity entity) {
        return new FranquiciaResponseDto(entity.getId(), entity.getNombre());
    }
}
