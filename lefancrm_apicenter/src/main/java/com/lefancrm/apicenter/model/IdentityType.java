package com.lefancrm.apicenter.model;

import java.io.Serializable;

public class IdentityType implements Serializable {

    private String val;

    private String text;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

