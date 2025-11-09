package com.javaweb.service;

import com.javaweb.model.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO addOrUpdateCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
}
