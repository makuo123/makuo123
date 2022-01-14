package com.stock.poitl.table;

import java.util.List;

public class OKRData {
    private User user;
    private List<OKRItem> Objectives;
    private List<OKRItem> ManageObjectives;
    private String date;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OKRItem> getObjectives() {
        return Objectives;
    }

    public void setObjectives(List<OKRItem> objectives) {
        Objectives = objectives;
    }

    public List<OKRItem> getManageObjectives() {
        return ManageObjectives;
    }

    public void setManageObjectives(List<OKRItem> manageObjectives) {
        ManageObjectives = manageObjectives;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
