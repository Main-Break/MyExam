package com.mainbreak.view;

import com.mainbreak.view.action.MenuAction;

public class Option {
    public int num;
    public String description;
    public MenuAction action;

    public Option(int num, String description, MenuAction action) {
        this.num = num;
        this.description = description;
        this.action = action;
    }

    public Option(int num, String description) {
        this.num = num;
        this.description = description;
    }
}