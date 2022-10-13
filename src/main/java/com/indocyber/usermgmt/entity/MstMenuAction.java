package com.indocyber.usermgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_menu_action")
public class MstMenuAction {
    @Id
    @Column(name = "action_id", nullable = false, length = 5)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}