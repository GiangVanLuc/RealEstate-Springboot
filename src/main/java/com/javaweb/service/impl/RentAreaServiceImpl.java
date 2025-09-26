package com.javaweb.service.impl;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentAreaServiceImpl implements RentAreaService {

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Override
    @Transactional
    public void deleteByBuildingId(List<Long> buildingids) {
        rentAreaRepository.deleteByBuildingIdIn(buildingids);
    }
}
