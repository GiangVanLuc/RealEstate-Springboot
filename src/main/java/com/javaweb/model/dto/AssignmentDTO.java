package com.javaweb.model.dto;

import java.util.List;

// DTO này nhận dữ liệu từ AJAX khi bấm nút "Giao khách hàng"
public class AssignmentDTO {

    private Long customerId;
    private List<Long> staffs; // Danh sách các staffId được chọn

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public List<Long> getStaffs() {
        return staffs;
    }
    public void setStaffs(List<Long> staffs) {
        this.staffs = staffs;
    }
}