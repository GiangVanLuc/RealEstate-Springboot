package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaConverter rentAreaConverter;

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity buildingEntity) {
        // modelMapper.map(nguon, dich)
        BuildingSearchResponse res = modelMapper.map(buildingEntity, BuildingSearchResponse.class);

        // set rentArea
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreaEntites();
        String areaResult = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        res.setRentArea(areaResult);


        // set Address
        Map<String, String> districts = District.type();

        String districtName = "";
        if(buildingEntity.getDistrict() != null && buildingEntity.getDistrict().length() > 0) {
            districtName = districts.get(buildingEntity.getDistrict());
        }

        if(districtName != null && districtName.length() > 0) {
            res.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtName);
        }

        return res;

    }


    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));
        buildingEntity.setRentAreaEntites(rentAreaConverter.toRentAreaEntityList(buildingDTO, buildingEntity));
        return buildingEntity;

    }

    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreaEntites();
        if (rentAreaEntities != null && !rentAreaEntities.isEmpty()) {
            buildingDTO.setRentArea(String.join(",",
                            rentAreaEntities.stream().map(it -> it.getValue() != null ? it.getValue().toString() : "").collect(Collectors.toList())));
        } else {
            buildingDTO.setRentArea("");
        }
        String typeString = buildingEntity.getType();
        if (typeString != null && !typeString.trim().isEmpty()) {
            List<String> typeCodes = Arrays.asList(typeString.trim().split("\\s+"));
            buildingDTO.setTypeCode(typeCodes);
        } else {
            buildingDTO.setTypeCode(Collections.emptyList());
        }
        return buildingDTO;
    }

}
