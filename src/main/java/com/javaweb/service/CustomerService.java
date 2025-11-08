package com.javaweb.service;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.CustomerSearchDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    
    void deleteCustomer(Long id);
    
    void deleteCustomers(List<Long> ids);
    
    CustomerDTO getCustomerById(Long id);
    
    Page<CustomerDTO> getAllCustomers(Integer page, Integer size);
    
    Page<CustomerDTO> searchCustomers(CustomerSearchDTO searchDTO);
    
    Map<String, Long> getStatisticsByCustomerType();
}
