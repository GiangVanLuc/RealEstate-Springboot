package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        if(customerDTO.getId() == null){
            customerEntity.setCreatedBy(customerDTO.getModifiedBy());
            customerEntity.setCreatedDate(customerDTO.getModifiedDate());
        }
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        customerEntity.setStatus(customerDTO.getStatus() != null ? customerDTO.getStatus() : "CHUA_XU_LY");
        customerRepository.save(customerEntity);
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> searchCustomers(MyUserDetail user, CustomerDTO customerDTO, Pageable pageable) {
        List<CustomerEntity> customerEntities = customerRepository.searchCustomer(user, customerDTO, pageable);

        UserEntity userEntity = userRepository.findOneByUserName(user.getUsername());

        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for(CustomerEntity customerEntity : customerEntities) {
            customerDTOS.add(customerConverter.convertToDto(customerEntity));
        }

        return customerDTOS;
    }

    @Override
    public Integer totalItems(MyUserDetail user, CustomerDTO customerDTO) {
        return customerRepository.totalSearchItems(user, customerDTO);
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        return customerConverter.convertToDto(customerEntity);
    }
}
