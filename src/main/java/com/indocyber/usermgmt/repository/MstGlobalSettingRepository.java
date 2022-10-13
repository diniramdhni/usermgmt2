package com.indocyber.usermgmt.repository;

import com.indocyber.usermgmt.entity.MstGlobalSetting;
import com.indocyber.usermgmt.entity.MstGlobalSettingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface MstGlobalSettingRepository extends JpaRepository<MstGlobalSetting, MstGlobalSettingId> {

    @Query("""
            SELECT glo
            FROM MstGlobalSetting AS glo
            WHERE glo.id.settingGroup = :settingGroup
            """)
    MstGlobalSetting findById_SettingGroup(@Param("settingGroup") String settingGroup);


}