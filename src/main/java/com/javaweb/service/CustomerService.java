package com.javaweb.service;

import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.MyUserDetail;

import com.javaweb.model.dto.StaffAssignmentDTO;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CustomerService {
    void addOrUpdateCustomer(CustomerDTO customerDTO);
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> searchCustomers(MyUserDetail user, CustomerDTO customerDTO, Pageable pageable);
    Integer totalItems(MyUserDetail user, CustomerDTO customerDTO);
    List<StaffAssignmentDTO> findStaffsByCustomerId(Long customerId);
    void assignCustomer(AssignmentDTO dto);
    void deleteCustomer(List<Long> ids);
    CustomerDTO findById(Long id);
}
