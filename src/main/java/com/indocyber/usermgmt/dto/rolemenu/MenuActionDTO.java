package com.indocyber.usermgmt.dto.rolemenu;

public class MenuActionDTO {

    private String actionId;

    public MenuActionDTO() {
    }
    public MenuActionDTO(String actionId) {
        this.actionId = actionId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }
}
