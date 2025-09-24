package com.javaweb.api.admin;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class  BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private BuildingRepository buildingRepository;

    @PostMapping
    public BuildingDTO addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        // xuong Db cap nhat hoac them
        return buildingDTO;
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids) {
        // Xuong Db de xoa building theo danh sach id gui ve
        buildingService.deleteBuildingById(ids);

    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO listStaffs(@PathVariable Long id) {
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
    }

    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        System.out.println("Ok");
    }


    @GetMapping("/test/{id}")
    public String testFindById(@PathVariable Long id) {
        System.out.println("==== BẮT ĐẦU TEST VỚI ID: " + id + " ====");
        try {
            // Gọi thẳng repository để kiểm tra, bỏ qua service
            BuildingEntity building = buildingRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("findById KHÔNG TÌM THẤY building với id: " + id));

            System.out.println("==== TEST API: TÌM THẤY building: " + building.getName() + " ====");
            return "OK! TÌM THẤY building: " + building.getName();

        } catch (Exception e) {
            System.err.println("==== TEST API: ĐÃ CÓ LỖI: " + e.getMessage() + " ====");
            return "LỖI! KHÔNG TÌM THẤY hoặc có lỗi khác: " + e.getMessage();
        }
    }

}
