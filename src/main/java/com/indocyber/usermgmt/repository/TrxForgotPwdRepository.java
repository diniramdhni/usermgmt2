package com.indocyber.usermgmt.repository;

import com.indocyber.usermgmt.dto.ResponseLoginDTO;
import com.indocyber.usermgmt.entity.TrxForgotPwd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrxForgotPwdRepository extends JpaRepository<TrxForgotPwd, Long> {

    @Query("""
            SELECT t
            FROM TrxForgotPwd AS t
                WHERE t.token = :token
            """)
    TrxForgotPwd findByToken(@Param("token") String token);
}