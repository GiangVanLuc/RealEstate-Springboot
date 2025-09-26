package com.javaweb.entity;


import javax.persistence.*;

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildingId;

    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity user;

    public BuildingEntity getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(BuildingEntity buildingId) {
        this.buildingId = buildingId;
    }

    public UserEntity getUserEntity() {
        return user;
    }

    public void setUserEntity(UserEntity user) {
        this.user = user;
    }
}
