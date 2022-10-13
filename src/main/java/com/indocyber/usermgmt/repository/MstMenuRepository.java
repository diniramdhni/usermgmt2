package com.indocyber.usermgmt.repository;

import com.indocyber.usermgmt.entity.MstMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstMenuRepository extends JpaRepository<MstMenu, String> {
}