package com.indocyber.usermgmt.dto;

public class ResponseLoginDTO {

    private String userId;
    private String name;
    private String token;

    public ResponseLoginDTO() {
    }
    public ResponseLoginDTO(String userId, String name, String token) {
        this.userId = userId;
        this.name = name;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
