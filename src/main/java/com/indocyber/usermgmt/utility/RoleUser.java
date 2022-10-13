package com.indocyber.usermgmt.utility;

import com.indocyber.usermgmt.entity.MstMenuAction;

import java.util.List;

public class RoleUser {

    private String roleId;
    private String menuId;
    private String menuTitle;
    private String menuUrl;
    private String menuParentId;
    private String menuSeq;
    private List<MstMenuAction> menuAction;

    public RoleUser() {
    }
    public RoleUser(String roleId, String menuId, String menuTitle, String menuUrl, String menuParentId, String menuSeq, List<MstMenuAction> menuAction) {
        this.roleId = roleId;
        this.menuId = menuId;
        this.menuTitle = menuTitle;
        this.menuUrl = menuUrl;
        this.menuParentId = menuParentId;
        this.menuSeq = menuSeq;
        this.menuAction = menuAction;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getMenuSeq() {
        return menuSeq;
    }

    public void setMenuSeq(String menuSeq) {
        this.menuSeq = menuSeq;
    }

    public List<MstMenuAction> getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(List<MstMenuAction> menuAction) {
        this.menuAction = menuAction;
    }
}
