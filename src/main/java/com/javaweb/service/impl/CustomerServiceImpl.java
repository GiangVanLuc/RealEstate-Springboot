package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.model.dto.StaffAssignmentDTO;
import com.javaweb.repository.AssignmentCustomerRepository;
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

    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;

    @Override
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        if(customerDTO.getId() == null){
            customerEntity.setCreatedBy(customerDTO.getModifiedBy());
            customerEntity.setCreatedDate(customerDTO.getModifiedDate());
        }
        customerEntity.setActive(true);
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
    public List<StaffAssignmentDTO> findStaffsByCustomerId(Long customerId) {
        // 1. Lấy khách hàng
        CustomerEntity customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Không tìm thấy khách hàng");
        }

        // 2. Lấy danh sách staff đã được gán cho khách hàng này
        List<AssignmentCustomerEntity> assignments = assignmentCustomerRepository.findByCustomer(customer);
        List<Long> assignedStaffIds = new ArrayList<>();
        for (AssignmentCustomerEntity item : assignments) {
            assignedStaffIds.add(item.getStaff().getId());
        }

        // 3. Lấy TẤT CẢ nhân viên (ROLE_STAFF)
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        // 4. Tạo DTO trả về
        List<StaffAssignmentDTO> response = new ArrayList<>();
        for (UserEntity staff : allStaffs) {
            StaffAssignmentDTO dto = new StaffAssignmentDTO();
            dto.setStaffId(staff.getId());
            dto.setFullName(staff.getFullName());

            // 5. Kiểm tra xem staff này có trong danh sách đã gán không
            if (assignedStaffIds.contains(staff.getId())) {
                dto.setChecked("checked");
            } else {
                dto.setChecked("");
            }
            response.add(dto);
        }
        return response;
    }

    @Override
    @Transactional // Rất quan trọng, để đảm bảo xóa và thêm cùng lúc
    public void assignCustomer(AssignmentDTO dto) {
        // 1. Lấy khách hàng
        CustomerEntity customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        // 2. XÓA tất cả các lượt gán cũ của khách hàng này
        assignmentCustomerRepository.deleteByCustomer(customer);

        // 3. THÊM MỚI các lượt gán
        List<AssignmentCustomerEntity> newAssignments = new ArrayList<>();
        for (Long staffId : dto.getStaffs()) {
            UserEntity staff = userRepository.findById(staffId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

            AssignmentCustomerEntity assignment = new AssignmentCustomerEntity();
            assignment.setCustomer(customer);
            assignment.setStaff(staff);
            newAssignments.add(assignment);
        }

        // 4. Lưu tất cả vào DB
        assignmentCustomerRepository.saveAll(newAssignments);
    }

    @Override
    public void deleteCustomer(List<Long> ids) {
        for(Long id : ids) {
            CustomerEntity customerEntity = customerRepository.findById(id).get();
            customerEntity.setActive(false);
            customerRepository.save(customerEntity);
        }

    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        return customerConverter.convertToDto(customerEntity);
    }
}
