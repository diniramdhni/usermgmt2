package com.indocyber.usermgmt.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trx_forgot_pwd")
public class TrxForgotPwd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_no", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 10)
    private String userId;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate;

    @Column(name = "token", nullable = false, length = 1000)
    private String token;

    @Column(name = "status", nullable = false, length = 1)
    private String status;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    public TrxForgotPwd(String userId, LocalDateTime requestDate, String token, String status, LocalDateTime expiredDate) {
        this.userId = userId;
        this.requestDate = requestDate;
        this.token = token;
        this.status = status;
        this.expiredDate = expiredDate;
    }

    public TrxForgotPwd() {
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
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