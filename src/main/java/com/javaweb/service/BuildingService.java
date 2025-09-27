package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BuildingService {
    ResponseDTO listStaffs(Long buildingId);
    void deleteBuildingById(List<Long> ids);
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    int countTotalItems(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO);
}
