package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

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

}
