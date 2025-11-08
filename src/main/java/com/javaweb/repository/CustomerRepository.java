package com.javaweb.repository;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    
    void deleteByIdIn(List<Long> ids);
    
    List<CustomerEntity> findByFullNameContainingIgnoreCase(String fullName);
    
    Optional<CustomerEntity> findByPhoneNumber(String phoneNumber);
    
    Optional<CustomerEntity> findByEmail(String email);
    
    List<CustomerEntity> findByCustomerType(CustomerType customerType);
    
    Page<CustomerEntity> findByIsActiveTrue(Pageable pageable);
    
    @Query("SELECT c FROM CustomerEntity c WHERE " +
           "(LOWER(c.fullName) LIKE LOWER(CONCAT('%', :searchValue, '%')) " +
           "OR c.phoneNumber LIKE CONCAT('%', :searchValue, '%') " +
           "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :searchValue, '%'))) " +
           "AND c.isActive = true")
    Page<CustomerEntity> searchCustomers(@Param("searchValue") String searchValue, Pageable pageable);
    
    @Query("SELECT COUNT(c) FROM CustomerEntity c WHERE c.customerType = :customerType AND c.isActive = true")
    Long countByCustomerType(@Param("customerType") CustomerType customerType);
}
