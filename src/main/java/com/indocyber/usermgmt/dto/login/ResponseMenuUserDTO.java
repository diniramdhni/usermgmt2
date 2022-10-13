package com.indocyber.usermgmt.dto.login;

import com.indocyber.usermgmt.entity.MstMenuAction;
import com.indocyber.usermgmt.utility.RoleUser;

import java.util.List;

public class ResponseMenuUserDTO {

    private List<RoleUser> roleUser;

    public ResponseMenuUserDTO() {
    }
    public ResponseMenuUserDTO(List<RoleUser> roleUser) {
        this.roleUser = roleUser;
    }

    public List<RoleUser> getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(List<RoleUser> roleUser) {
        this.roleUser = roleUser;
    }
}
