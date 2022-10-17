package com.indocyber.usermgmt.repository;

import com.indocyber.usermgmt.entity.MstBranch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MstBranchRepository extends JpaRepository<MstBranch, String> {

    @Query("""
            SELECT br
            FROM MstBranch br
            WHERE UPPER(br.name) LIKE %:name% 
                AND UPPER(br.id) LIKE %:id% 
                AND UPPER(br.type) LIKE %:type% 
                AND UPPER(br.address) LIKE %:address%
                AND (UPPER(br.createdBy) LIKE %:createdBy% OR br.createdBy = null) 
                AND (UPPER(br.updatedBy) LIKE %:updatedBy% OR br.updatedBy = null)
            """)
    public Page<MstBranch> findAllBranch(Pageable page,
                                  @Param("name") String name,
                                  @Param("id") String id,
                                  @Param("type") String type,
                                  @Param("address") String address,
                                  @Param("createdBy") String createdBy,
                                  @Param("updatedBy") String updatedBy);
}
