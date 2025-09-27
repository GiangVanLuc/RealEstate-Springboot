package com.javaweb.service;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingService  {
    void deleteAssignmentBuildingEntity(List<Long> ids);
    void addAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO);
}
