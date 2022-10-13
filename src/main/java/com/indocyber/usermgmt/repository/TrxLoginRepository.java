package com.indocyber.usermgmt.repository;

import com.indocyber.usermgmt.dto.login.ForgotPasswordResponseDTO;
import com.indocyber.usermgmt.entity.TrxLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


public interface TrxLoginRepository extends JpaRepository<TrxLogin, Long> {

    @Query("""
            SELECT tx
            FROM TrxLogin tx
            WHERE tx.userId = :userId 
                AND tx.logoutDate Is null
            """
    )
    TrxLogin findByUserIdNull(@Param("userId") String userId);

    @Query("""
            SELECT COUNT(*)
            FROM TrxLogin tx
            WHERE tx.userId = :userId 
                AND tx.logoutDate Is null
            """
    )
    Long checkLogoutByCount(@Param("userId") String userId);

    @Transactional
    @Modifying
    @Query("""
            UPDATE TrxLogin trx
            SET trx.logoutDate = :logoutDate
            WHERE trx.userId = :userId 
                AND trx.logoutDate Is null
            """)
    void updateLogout(@Param("logoutDate") LocalDateTime logoutDate,
                      @Param("userId") String userId);


    @Transactional
    @Modifying
    @Query("""
            UPDATE MstUser us
            SET us.password = :newpwd
            WHERE us.id = :userId
            """)
    void updatePassword(@Param("newpwd") String newpwd,
                        @Param("userId") String userId);


}
