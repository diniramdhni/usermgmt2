package com.indocyber.usermgmt.service;

import com.indocyber.usermgmt.dto.branch.InsertBranchDTO;
import com.indocyber.usermgmt.dto.branch.UpdateBranchDTO;
import com.indocyber.usermgmt.entity.MstBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MstBranchService {
    MstBranch insertBranch(InsertBranchDTO dto);


    MstBranch getBranchById(String branchId);

    MstBranch updateBranchById(UpdateBranchDTO updateDto, String id);

    void deleteById(String id);



    Page<MstBranch> getAllBranch(Pageable pageable, String name, String id, String type, String address, String createdBy, String updatedBy);
}
