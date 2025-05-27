package com.lefancrm.apicenter.vo;

import java.io.Serializable;

public class ResultVO<T> implements Serializable {

    private boolean success;

    private String message;

    private T entity;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
