package com.javaweb.api.admin;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class  BuildingAPI {

    @Autowired
    private BuildingService buildingService;





    @PostMapping
    public ResponseEntity<BuildingDTO> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        // xuong Db cap nhat hoac them
        return ResponseEntity.ok(buildingService.addOrUpdateBuilding(buildingDTO));

    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable List<Long> ids) {
        buildingService.deleteBuildingById(ids);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO listStaffs(@PathVariable Long id) {
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
    }

    @PutMapping("/assignment")
    public ResponseEntity<Void> updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        buildingService.assignBuildingEntity(assignmentBuildingDTO);
        return ResponseEntity.ok().build();
    }


}
