package com.javaweb.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "ward")
    private String ward;

    @Column(name = "district")
    private String district;

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "floorarea")
    private Long floorArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private Long level;

    @Column(name = "rentprice")
    private Long rentPrice;

    @Column(name = "rentpricedescription")
    private String rentPriceDescription;

    @Column(name = "servicefee")
    private String serviceFee;
    @Column(name = "street")
    private String street;

    @Column(name = "carfee")
    private Long carFee;

    @Column(name = "motofee")
    private Long motoFee;

    @Column(name = "overtimefee")
    private Long overTimeFee;

    @Column(name = "waterfee")
    private Long waterFee;

    @Column(name = "electricityfee")
    private Long electricityFee;

    @Column(name = "deposit")
    private Long deposit;

    @Column(name = "payment")
    private Long payment;

    @Column(name = "renttime")
    private Long rentTime;

    @Column(name = "decorationtime")
    private Long decorationTime;

    @Column(name = "brokeragefee")
    private Long brokerageFee;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphonenumber")
    private String managerPhoneNumber;

    @OneToMany(mappedBy="building", fetch = FetchType.LAZY)
    private List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();


    public Long getNumberofbasement() {
        return numberOfBasement;
    }
    public void setNumberofbasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }
    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }


    public Long getFloorArea() {
        return floorArea;
    }
    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }
    //	public String getEmptyArea() {
//		return emptyArea;
//	}
//	public void setEmptyArea(String emptyArea) {
//		this.emptyArea = emptyArea;
//	}
    public Long getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }
    public String getServiceFee() {
        return serviceFee;
    }
    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }
    public Long getBrokerageFee() {
        return brokerageFee;
    }
    public void setBrokerageFee(Long brokerageFee) {
        this.brokerageFee = brokerageFee;
    }


}
