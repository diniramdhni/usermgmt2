package com.indocyber.usermgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mst_menu")
public class MstMenu {
    @Id
    @Column(name = "menu_id", nullable = false, length = 5)
    private String id;

    @Column(name = "menu_title", nullable = false, length = 50)
    private String menuTitle;

    @Column(name = "menu_url", length = 200)
    private String menuUrl;

    @Column(name = "menu_parent_id", length = 5)
    private String menuParentId;

    @Column(name = "menu_seq", nullable = false)
    private Integer menuSeq;

    public Integer getMenuSeq() {
        return menuSeq;
    }

    public void setMenuSeq(Integer menuSeq) {
        this.menuSeq = menuSeq;
    }

    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}