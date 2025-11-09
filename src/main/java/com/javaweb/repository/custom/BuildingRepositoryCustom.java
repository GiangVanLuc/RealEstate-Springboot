package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    //List<BuildingSearchResponse> findAll(BuildingSearchBuilder buildingSearchBuilder);
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchbuilder, Pageable pageable);

    int countTotalItems(BuildingSearchBuilder buildingSearchBuilder);
}
