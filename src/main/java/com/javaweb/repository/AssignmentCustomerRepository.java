package com.javaweb.repository;

import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity, Long> {
    // Tìm tất cả các 'lượt giao' của một khách hàng
    List<AssignmentCustomerEntity> findByCustomer(CustomerEntity customer);

    // Xóa tất cả các 'lượt giao' cũ của một khách hàng (dùng khi gán lại)
    void deleteByCustomer(CustomerEntity customer);

    // (Optional) Tìm bằng customerId
    List<AssignmentCustomerEntity> findByCustomer_Id(Long customerId);
}
