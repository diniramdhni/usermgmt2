package com.indocyber.usermgmt.dto.branch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateBranchDTO {

    @NotBlank(message="Id is required.")
    private String id;

    @NotBlank(message="Branch Name is required.")
    private String name;

    @NotBlank(message="Branch Type is required.")
    private String type;

    @NotBlank(message="Branch Address is required.")
    private String address;

    @NotNull(message="Flag active is required.")
    private boolean flag_active;




    public UpdateBranchDTO() {
    }

    public UpdateBranchDTO(String id, String name, String type, String address, boolean flag_active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
        this.flag_active = flag_active;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFlag_active() {
        return flag_active;
    }

    public void setFlag_active(boolean flag_active) {
        this.flag_active = flag_active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
