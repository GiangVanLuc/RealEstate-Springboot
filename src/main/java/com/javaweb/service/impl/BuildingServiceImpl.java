package com.javaweb.service.impl;

import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        return null;
    }

    @Override
    @Transactional // Đảm bảo toàn bộ hoạt động xóa nằm trong một transaction
    public void deleteBuildingById(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            buildingRepository.deleteByIdIn(ids);
        }
    }


}
