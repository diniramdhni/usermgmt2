package com.indocyber.usermgmt.dto.login;

public class CheckAuthDTO {

    private String menuId;
    private String menuAction;

    public CheckAuthDTO() {
    }
    public CheckAuthDTO(String menuId, String menuAction) {
        this.menuId = menuId;
        this.menuAction = menuAction;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }
}
