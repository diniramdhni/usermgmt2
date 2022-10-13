package com.indocyber.usermgmt.repository;

import com.indocyber.usermgmt.dto.rolemenu.RoleMenuDTO;
import com.indocyber.usermgmt.entity.MstRoleMenu;
import com.indocyber.usermgmt.entity.MstRoleMenuId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MstRoleMenuRepository extends JpaRepository<MstRoleMenu, MstRoleMenuId> {
    List<MstRoleMenu> findById_RoleId(String roleId);

//    @Query("""
//            SELECT new com.indocyber.usermgmtrolemenu.dto.RoleMenuDTO(
//            rmi.roleId, rmi.menuId, rmi.actionId, rm.createdDate, rm.createdBy, rm.updatedDate, rm.updatedBy)
//            FROM RoleMenu AS rm
//                JOIN rm.id AS rmi
//            WHERE rmi.roleId = :roleId
//            """)
//    List<RoleMenuDTO> findByRoleId(@Param("roleId") String roleId);
//
//    @Query("""
//            SELECT new com.indocyber.usermgmtrolemenu.dto.RoleMenuDTO(
//            rmi.roleId, rmi.menuId, rmi.actionId, rm.createdDate, rm.createdBy, rm.updatedDate, rm.updatedBy)
//            FROM RoleMenu AS rm
//                JOIN rm.id AS rmi
//            WHERE rmi.roleId = :roleId
//                AND rmi.menuId = :menuId
//                AND rmi.actionId = :actionId
//            """)
//    RoleMenuDTO findRoleMenu(@Param("roleId") String roleId,@Param("menuId") String menuId,@Param("actionId") String actionId);
//
//    @Query("""
//            SELECT rm
//            FROM RoleMenu AS rm
//            WHERE rm.id.roleId LIKE %:roleId%
//                AND rm.id.menuId LIKE %:menuId%
//                AND rm.id.actionId LIKE %:actionId%
//            """)
//    List<MstRoleMenu> getAllRoleMenu(@Param("roleId")String roleId, @Param("menuId")String menuId,
//                                     @Param("actionId")String actionId, Pageable pagination);
//
//    @Query("""
//            SELECT COUNT(*)
//            FROM RoleMenu AS rm
//            WHERE rm.id.roleId LIKE %:roleId%
//                AND rm.id.menuId LIKE %:menuId%
//                AND rm.id.actionId LIKE %:actionId%
//            """)
//    long countRoleMenu(@Param("roleId") String roleId,@Param("menuId")String menuId,
//                       @Param("actionId")String actionId);
}