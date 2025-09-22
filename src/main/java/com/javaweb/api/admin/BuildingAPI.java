package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class  BuildingAPI {

    @Autowired
    private BuildingService buildingService;
    @PostMapping
    public BuildingDTO addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        // xuong Db cap nhat hoac them
        return buildingDTO;
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids) {
        // Xuong Db de xoa building theo danh sach id gui ve
//        buildingService.deleteBuildingById(ids);

    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO listStaffs(@PathVariable Long id) {
        ResponseDTO result = buildingService.listStaffs(id);
        return null;
    }

}
