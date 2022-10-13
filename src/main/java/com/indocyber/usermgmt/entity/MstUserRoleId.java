package com.indocyber.usermgmt.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class MstUserRoleId implements Serializable {
    private static final long serialVersionUID = -2618737931751485340L;
    @Column(name = "user_id", nullable = false, length = 10)
    private String userId;
    @Column(name = "role_id", nullable = false, length = 10)
    private String roleId;
    @Column(name = "branch_id", nullable = false, length = 10)
    private String branchId;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, roleId, userId, startDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MstUserRoleId entity = (MstUserRoleId) o;
        return Objects.equals(this.branchId, entity.branchId) &&
                Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.startDate, entity.startDate);
    }
}