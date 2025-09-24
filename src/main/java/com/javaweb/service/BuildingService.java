package com.javaweb.service;

import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.awt.print.Pageable;
import java.util.List;

public interface BuildingService {
    ResponseDTO listStaffs(Long buildingId);
    void deleteBuildingById(List<Long> ids);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
}
