package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.CustomerType;
import com.javaweb.exception.CustomerNotFoundException;
import com.javaweb.exception.DuplicateCustomerException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.CustomerSearchDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        // Validate duplicate email
        if (customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty()) {
            Optional<CustomerEntity> existingByEmail = customerRepository.findByEmail(customerDTO.getEmail());
            if (existingByEmail.isPresent()) {
                throw new DuplicateCustomerException("Email đã tồn tại trong hệ thống: " + customerDTO.getEmail());
            }
        }

        // Validate duplicate phone number
        if (customerDTO.getPhoneNumber() != null && !customerDTO.getPhoneNumber().isEmpty()) {
            Optional<CustomerEntity> existingByPhone = customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber());
            if (existingByPhone.isPresent()) {
                throw new DuplicateCustomerException("Số điện thoại đã tồn tại trong hệ thống: " + customerDTO.getPhoneNumber());
            }
        }

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFullName(customerDTO.getFullName());
        customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setAddress(customerDTO.getAddress());
        customerEntity.setNotes(customerDTO.getNotes());
        customerEntity.setCompanyName(customerDTO.getCompanyName());
        customerEntity.setDemand(customerDTO.getDemand());
        customerEntity.setStatus(customerDTO.getStatus());
        customerEntity.setIsActive(true);

        // Set customer type
        if (customerDTO.getCustomerType() != null && !customerDTO.getCustomerType().isEmpty()) {
            try {
                customerEntity.setCustomerType(CustomerType.valueOf(customerDTO.getCustomerType()));
            } catch (IllegalArgumentException e) {
                customerEntity.setCustomerType(CustomerType.POTENTIAL);
            }
        } else {
            customerEntity.setCustomerType(CustomerType.POTENTIAL);
        }

        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return convertToDTO(savedEntity);
    }

    @Override
    @Transactional
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        CustomerEntity existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Không tìm thấy khách hàng với ID: " + id));

        // Validate duplicate email (excluding current customer)
        if (customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty()) {
            Optional<CustomerEntity> existingByEmail = customerRepository.findByEmail(customerDTO.getEmail());
            if (existingByEmail.isPresent() && !existingByEmail.get().getId().equals(id)) {
                throw new DuplicateCustomerException("Email đã tồn tại trong hệ thống: " + customerDTO.getEmail());
            }
        }

        // Validate duplicate phone number (excluding current customer)
        if (customerDTO.getPhoneNumber() != null && !customerDTO.getPhoneNumber().isEmpty()) {
            Optional<CustomerEntity> existingByPhone = customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber());
            if (existingByPhone.isPresent() && !existingByPhone.get().getId().equals(id)) {
                throw new DuplicateCustomerException("Số điện thoại đã tồn tại trong hệ thống: " + customerDTO.getPhoneNumber());
            }
        }

        existingCustomer.setFullName(customerDTO.getFullName());
        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setAddress(customerDTO.getAddress());
        existingCustomer.setNotes(customerDTO.getNotes());
        existingCustomer.setCompanyName(customerDTO.getCompanyName());
        existingCustomer.setDemand(customerDTO.getDemand());
        existingCustomer.setStatus(customerDTO.getStatus());

        // Update customer type
        if (customerDTO.getCustomerType() != null && !customerDTO.getCustomerType().isEmpty()) {
            try {
                existingCustomer.setCustomerType(CustomerType.valueOf(customerDTO.getCustomerType()));
            } catch (IllegalArgumentException e) {
                // Keep existing type if invalid
            }
        }

        CustomerEntity updatedEntity = customerRepository.save(existingCustomer);
        return convertToDTO(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Không tìm thấy khách hàng với ID: " + id));
        
        // Soft delete
        customer.setIsActive(false);
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomers(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Long id : ids) {
                try {
                    deleteCustomer(id);
                } catch (CustomerNotFoundException e) {
                    // Continue with other deletions
                }
            }
        }
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Không tìm thấy khách hàng với ID: " + id));
        return convertToDTO(customer);
    }

    @Override
    public Page<CustomerDTO> getAllCustomers(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
        Page<CustomerEntity> customerPage = customerRepository.findByIsActiveTrue(pageable);
        return customerPage.map(this::convertToDTO);
    }

    @Override
    public Page<CustomerDTO> searchCustomers(CustomerSearchDTO searchDTO) {
        Integer page = searchDTO.getPage() != null ? searchDTO.getPage() : 0;
        Integer size = searchDTO.getSize() != null ? searchDTO.getSize() : 10;
        Pageable pageable = PageRequest.of(page, size);

        Page<CustomerEntity> customerPage;
        
        if (searchDTO.getSearchValue() != null && !searchDTO.getSearchValue().isEmpty()) {
            customerPage = customerRepository.searchCustomers(searchDTO.getSearchValue(), pageable);
        } else {
            customerPage = customerRepository.findByIsActiveTrue(pageable);
        }

        return customerPage.map(this::convertToDTO);
    }

    @Override
    public Map<String, Long> getStatisticsByCustomerType() {
        Map<String, Long> statistics = new HashMap<>();
        
        for (CustomerType type : CustomerType.values()) {
            Long count = customerRepository.countByCustomerType(type);
            statistics.put(type.name(), count != null ? count : 0L);
        }
        
        return statistics;
    }

    private CustomerDTO convertToDTO(CustomerEntity entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setNotes(entity.getNotes());
        dto.setCompanyName(entity.getCompanyName());
        dto.setDemand(entity.getDemand());
        dto.setStatus(entity.getStatus());
        dto.setIsActive(entity.getIsActive());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        
        if (entity.getCustomerType() != null) {
            dto.setCustomerType(entity.getCustomerType().name());
        }
        
        return dto;
    }
}
