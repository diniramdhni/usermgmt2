package com.indocyber.usermgmt.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MstRoleMenuId implements Serializable {
    private static final long serialVersionUID = -7003970267911832795L;
    @Column(name = "role_id", nullable = false, length = 10)
    private String roleId;
    @Column(name = "menu_id", nullable = false, length = 10)
    private String menuId;
    @Column(name = "action_id", nullable = false, length = 5)
    private String actionId;

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, menuId, actionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MstRoleMenuId entity = (MstRoleMenuId) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.menuId, entity.menuId) &&
                Objects.equals(this.actionId, entity.actionId);
    }
}