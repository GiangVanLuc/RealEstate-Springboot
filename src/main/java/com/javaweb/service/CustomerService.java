package com.javaweb.service;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.MyUserDetail;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CustomerService {
    void addOrUpdateCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> searchCustomers(MyUserDetail user, CustomerDTO customerDTO, Pageable pageable);
    Integer totalItems(MyUserDetail user, CustomerDTO customerDTO);
//    void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO);
//    ResponseDTO listStaffs(Long customerId);
//    void deleteCustomerByIds(List<Long> ids);
    CustomerDTO findById(Long id);
}
