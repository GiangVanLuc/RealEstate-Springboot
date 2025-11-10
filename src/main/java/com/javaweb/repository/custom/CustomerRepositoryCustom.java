package com.javaweb.repository.custom;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.MyUserDetail;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> searchCustomer(MyUserDetail userDetail, CustomerDTO customerDTO, Pageable pageable);

    Integer totalSearchItems(MyUserDetail userDetail, CustomerDTO customerDTO);
}
