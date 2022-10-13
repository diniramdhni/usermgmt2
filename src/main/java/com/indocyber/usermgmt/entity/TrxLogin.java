package com.indocyber.usermgmt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "trx_login")
public class TrxLogin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_no", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 10)
    private String userId;

    @Column(name = "login_date", nullable = false)
    private LocalDateTime loginDate;

    @Column(name = "jwt_token", nullable = false, length = 1000)
    private String jwtToken;

    @Column(name = "jwt_token_refresh", length = 1000)
    private String jwtTokenRefresh;

    @Column(name = "logout_date")
    private LocalDateTime logoutDate;

    public TrxLogin() {
    }

    public TrxLogin(String userId,
                    LocalDateTime loginDate,
                    String jwtToken,
                    String jwtTokenRefresh,
                    LocalDateTime logoutDate) {
        this.userId = userId;
        this.loginDate = loginDate;
        this.jwtToken = jwtToken;
        this.jwtTokenRefresh = jwtTokenRefresh;
        this.logoutDate = logoutDate;
    }

    public LocalDateTime getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(LocalDateTime logoutDate) {
        this.logoutDate = logoutDate;
    }

    public String getJwtTokenRefresh() {
        return jwtTokenRefresh;
    }

    public void setJwtTokenRefresh(String jwtTokenRefresh) {
        this.jwtTokenRefresh = jwtTokenRefresh;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}