package com.indocyber.usermgmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class
RequestDTO {

    private String userId;
    private String password;

    @JsonIgnore
    private String SECRET_KEY = "Animus-est-sicut lux-solis-Cum-in-eo-sit-omnia-revelat";
    @JsonIgnore
    private String audience = "usermgmtAudience";

    @JsonIgnore
    private String subject = "usermgmtSubject";


    public RequestDTO(){}

    public RequestDTO(String userId, String password, String SECRET_KEY, String audience) {
        this.userId = userId;
        this.password = password;
        this.SECRET_KEY = SECRET_KEY;
        this.audience = audience;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    public void setSECRET_KEY(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
