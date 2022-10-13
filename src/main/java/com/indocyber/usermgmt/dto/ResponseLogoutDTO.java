package com.indocyber.usermgmt.dto;

public class ResponseLogoutDTO {

    private String userId;

    public ResponseLogoutDTO() {
    }

    public ResponseLogoutDTO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
