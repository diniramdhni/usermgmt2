package com.indocyber.usermgmt.dto.login;

public class ProcessForgotPwdDTO {

    private final String userId;
    private final String token;
    private final String password;

    public ProcessForgotPwdDTO(String userId, String token, String password) {
        this.userId = userId;
        this.token = token;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }
}
