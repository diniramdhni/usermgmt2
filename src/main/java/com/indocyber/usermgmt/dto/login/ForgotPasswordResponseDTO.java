package com.indocyber.usermgmt.dto.login;

public class ForgotPasswordResponseDTO {

    private final String userId;
    private final String link;

    public ForgotPasswordResponseDTO(String userId, String link) {
        this.userId = userId;
        this.link = link;
    }

    public String getUserId() {
        return userId;
    }

    public String getLink() {
        return link;
    }
}
