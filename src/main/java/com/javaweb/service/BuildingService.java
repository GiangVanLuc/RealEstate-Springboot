package com.javaweb.service;

import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface BuildingService {
    ResponseDTO listStaffs(Long buildingId);
    void deleteBuildingById(List<Long> ids);
}
