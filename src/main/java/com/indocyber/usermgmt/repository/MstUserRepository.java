package com.indocyber.usermgmt.repository;

import com.indocyber.usermgmt.entity.MstUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstUserRepository extends JpaRepository<MstUser, String> {

}
