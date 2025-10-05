package com.javaweb.converter;


import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {
    public RentAreaEntity toRentAreaEntity(Long val, BuildingEntity buildingEntity) {
        RentAreaEntity res = new RentAreaEntity();
        res.setBuildingId(buildingEntity);
        res.setValue(val);
        return res;
    }
    public List<RentAreaEntity> toRentAreaEntityList(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {

        String[] rentArea = buildingDTO.getRentArea().split(",");
        List<RentAreaEntity> rentAreaEntityList = new ArrayList<>();

        for(String val: rentArea){
            rentAreaEntityList.add(toRentAreaEntity(Long.valueOf(val), buildingEntity));
        }
        return rentAreaEntityList;
    }

}