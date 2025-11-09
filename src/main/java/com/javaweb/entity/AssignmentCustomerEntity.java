package com.javaweb.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "assignmentcustomer")
public class AssignmentCustomerEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staffid", nullable = false, foreignKey = @ForeignKey(name = "FK_assignment_staff"))
    private UserEntity staff;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerid", nullable = false, foreignKey = @ForeignKey(name = "FK_assignment_customer"))
    private CustomerEntity customer;

    public UserEntity getStaff() {
        return staff;
    }

    public void setStaff(UserEntity staff) {
        this.staff = staff;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }


}
