package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO addOrUpdateCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFullName(customerDTO.getFullName());
        customerEntity.setPhone(customerDTO.getCustomerPhone());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setCompanyName(customerDTO.getCompanyName());
        customerEntity.setDemand(customerDTO.getDemand());
        customerEntity.setStatus(customerDTO.getStatus() != null ? customerDTO.getStatus() : "NEW"); // default status

        customerRepository.save(customerEntity);
        return customerDTO;
    }
}
