package com.soniq.mapper;

import com.soniq.dto.BuildingDTO;
import com.soniq.dto.ResultDTO;
import com.soniq.entity.Building;
import com.soniq.entity.Result;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author R.fatthi
 */
@org.mapstruct.Mapper
public interface Mapper {

    @Mapping(target = "rooms", source = "rooms")
    Building toBuilding(BuildingDTO buildingDTO);

    ResultDTO toResultDTO(Result result);

    default List<ResultDTO> toResultDTOs(List<Result> results) {
        return results.stream()
                .map(result -> toResultDTO(result))
                .collect(Collectors.toList());
    }
}
