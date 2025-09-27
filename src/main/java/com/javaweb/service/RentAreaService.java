package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;

import java.util.List;

public interface RentAreaService {
    void deleteByBuildingId(List<Long> ids);
    void addRentArea(BuildingDTO buildingDTO);
}
