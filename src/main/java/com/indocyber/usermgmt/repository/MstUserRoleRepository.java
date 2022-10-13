package com.indocyber.usermgmt.repository;

import com.indocyber.usermgmt.entity.MstUserRole;
import com.indocyber.usermgmt.entity.MstUserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MstUserRoleRepository extends JpaRepository<MstUserRole, MstUserRoleId> {

    @Query("select m from MstUserRole m where m.id.userId = ?1")
    Optional<MstUserRole> findById_UserId(String userId);

}