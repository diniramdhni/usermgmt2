package com.indocyber.usermgmt.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MstGlobalSettingId implements Serializable {
    private static final long serialVersionUID = 923001605681396507L;
    @Column(name = "setting_group", nullable = false, length = 30)
    private String settingGroup;
    @Column(name = "setting_code", nullable = false, length = 10)
    private String settingCode;

    public String getSettingCode() {
        return settingCode;
    }

    public void setSettingCode(String settingCode) {
        this.settingCode = settingCode;
    }

    public String getSettingGroup() {
        return settingGroup;
    }

    public void setSettingGroup(String settingGroup) {
        this.settingGroup = settingGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(settingCode, settingGroup);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MstGlobalSettingId entity = (MstGlobalSettingId) o;
        return Objects.equals(this.settingCode, entity.settingCode) &&
                Objects.equals(this.settingGroup, entity.settingGroup);
    }
}