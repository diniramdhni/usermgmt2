package com.indocyber.usermgmt.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mst_global_setting")
public class MstGlobalSetting {
    @EmbeddedId
    private MstGlobalSettingId id;

    @Column(name = "setting_value", length = 100)
    private String settingValue;

    @Column(name = "flag_active", nullable = false)
    private Boolean flagActive = false;

    public Boolean getFlagActive() {
        return flagActive;
    }

    public void setFlagActive(Boolean flagActive) {
        this.flagActive = flagActive;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public MstGlobalSettingId getId() {
        return id;
    }

    public void setId(MstGlobalSettingId id) {
        this.id = id;
    }
}