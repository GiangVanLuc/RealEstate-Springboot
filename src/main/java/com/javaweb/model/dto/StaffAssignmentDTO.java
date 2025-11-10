package com.javaweb.model.dto;

// DTO này chứa thông tin staff để hiển thị trên modal
public class StaffAssignmentDTO {

    private Long staffId;
    private String fullName;
    private String checked; // "checked" hoặc ""

    // Getters and Setters
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getChecked() {
        return checked;
    }
    public void setChecked(String checked) {
        this.checked = checked;
    }
}