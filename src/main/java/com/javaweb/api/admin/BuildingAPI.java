package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class  BuildingAPI {
    @PostMapping
    public BuildingDTO addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        // xuong Db cap nhat hoac them
        return buildingDTO;
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids) {
        // Xuong Db de xoa building theo danh sach id gui ve
        System.out.println("ok");
    }
}
