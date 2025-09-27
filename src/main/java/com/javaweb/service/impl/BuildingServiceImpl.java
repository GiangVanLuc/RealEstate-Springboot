package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import com.javaweb.utils.NumberUtils;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaService rentAreaService;


    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy tòa nhà với ID: " + buildingId));
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<AssignmentBuildingEntity> assignmentBuildingEntities = building.getAssignmentBuildingEntities();
        List<UserEntity> staffAssignment = new ArrayList<>();
        for(AssignmentBuildingEntity assignmentBuildingEntity : assignmentBuildingEntities){
            staffAssignment.add(assignmentBuildingEntity.getUserEntity());
        }


        List<StaffResponseDTO> staffResponseDTOs = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it: staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(it.getId());
            staffResponseDTO.setFullName(it.getFullName());
            if(staffAssignment.contains(it)) {
                staffResponseDTO.setChecked("checked");
            }
            else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOs.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOs);
        responseDTO.setMessage("success");
        return responseDTO;

    }

    @Override
    @Transactional // Đảm bảo toàn bộ hoạt động xóa nằm trong một transaction
    public void deleteBuildingById(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            buildingRepository.deleteByIdIn(ids);
        }
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        List<String> typeCode = buildingSearchRequest.getTypeCode();
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest, typeCode);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder, pageable);
        List<BuildingSearchResponse> res = new ArrayList<>();

        for(BuildingEntity item: buildingEntities) {
            BuildingSearchResponse building = buildingConverter.toBuildingSearchResponse(item);
            res.add(building);
        }
        return res;
    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItems(
                buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest, buildingSearchRequest.getTypeCode())
        );
    }


    public static boolean checkAddBuilding(BuildingDTO buildingDTO) {
//        if(!StringUtils.check(buildingDTO.getName())) return false;
//        if(!StringUtils.check(buildingDTO.getName())) return false;
//        if(!StringUtils.check(buildingDTO.getName())) return false;
//        if(!StringUtils.check(buildingDTO.getName())) return false;
//        if(!StringUtils.check(buildingDTO.getName())) return false;
//        if(!StringUtils.check(buildingDTO.getName())) return false;

//        if(!NumberUtils.isLong(buildingDTO.getNumberOfBasement())) return false;
//        if(!NumberUtils.isLong(buildingDTO.getFloorArea())) return false;
//          if(!NumberUtils.isLong(buildingDTO.getRentPrice())) return false;
        return true;
    }

    @Override
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO) {
        if(!checkAddBuilding(buildingDTO)){
            return null;
        }
        Long buildingId = buildingDTO.getId();
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));
//        if(buildingId != null) {
//            BuildingEntity foundBuilding = buildingRepository.findById(buildingId).orElseThrow(() -> new NotFoundException("Building not found!"));
//            buildingEntity.setImage(foundBuilding.getImage());
//        }
//        saveThumbnail(buildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
        if(StringUtils.check(buildingDTO.getRentArea())) rentAreaService.addRentArea(buildingDTO);
        return buildingDTO;
    }

//    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
//        String path = "/building/" + buildingDTO.getImageName();
//
//        if (null != buildingDTO.getImageBase64()) {
//            if (null != buildingEntity.getImage()) {
//                if (!path.equals(buildingEntity.getImage())) {
//                    File file = new File("C://home/office" + buildingEntity.getImage());
//                    file.delete();
//                }
//            }
//            byte[] bytes = Base64.getDecoder().decode(buildingDTO.getImageBase64());
//            // Giả định có một lớp tiện ích để ghi tệp
//            uploadFileUtils.writeOrUpdate(path, bytes);
//            buildingEntity.setImage(path);
//        }
//
//    }


}
