package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToDto(CustomerEntity e) {
        if (e == null) return null;

        CustomerDTO dto = modelMapper.map(e, CustomerDTO.class);

        dto.setCustomerPhone(e.getPhone());

        return dto;
    }

    public CustomerEntity convertToEntity(CustomerDTO dto) {
        if (dto == null) return null;

        CustomerEntity e = modelMapper.map(dto, CustomerEntity.class);

        e.setPhone(dto.getCustomerPhone());

        return e;
    }

    public void updateEntity(CustomerEntity e, CustomerDTO dto) {
        if (e == null || dto == null) return;
        modelMapper.map(dto, e);
        e.setPhone(dto.getCustomerPhone());
    }
}