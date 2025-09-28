package com.accenture.franquicia.domain.port.in;

import com.accenture.franquicia.domain.model.franquicia.FranquiciaNombreUpdateRequestDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaRequestDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaResponseDto;
import com.accenture.franquicia.domain.model.franquicia.FranquiciaSucursalMaxStockPorProductoResponseDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalRequestDto;
import com.accenture.franquicia.domain.model.franquiciasucursal.FranquiciaSucursalResponseDto;
import reactor.core.publisher.Mono;

public interface FranquiciaIn {

    /**
     * Se utiliza para guardar una franquicia
     * @param request el nombre de la franquicia
     * @return la informacion de la franquicia creada
     */
    Mono<FranquiciaResponseDto> save(Mono<FranquiciaRequestDto> request);

    /**
     * Se utiliza para crear una sucursal de una franquicia
     * @param franquiciaSucursalRequestDtoMono el identificador de la franquicia y el nombre de la nueva sucursal
     * @return la informacion de la franquicia con las sucursales que tiene registradas
     */
    Mono<FranquiciaSucursalResponseDto> franquiciaSucursalSave(Mono<FranquiciaSucursalRequestDto> franquiciaSucursalRequestDtoMono);

    /**
     * Se utiliza para calcular el max stock de los productos por cada una de las sucursales de la franquicia enviada
     * @param id informacion de la franquicia
     * @return el resultado de la consulta
     */
    Mono<FranquiciaSucursalMaxStockPorProductoResponseDto> maxStockPorProducto(Long id);


    /**
     * Se utiliza para actualizar el nombre de la franquicia
     * @param id el id de la franquicia a actualizar
     * @param request el nombre de la franquicia a actualizar
     * @return la informacion de la franquicia actualizada
     */
    Mono<FranquiciaResponseDto> nombreUpdate(Long id, Mono<FranquiciaNombreUpdateRequestDto> request);
}
