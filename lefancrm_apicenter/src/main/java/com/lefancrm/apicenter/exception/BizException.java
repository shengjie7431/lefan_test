package com.lefancrm.apicenter.exception;

public class BizException extends Exception {

    public BizException(){
        super();

    }

    public BizException(String message) {
        super(message);

    }

    public BizException(Throwable throwable) {
        super(throwable);

    }

    public BizException(String message, Throwable throwable) {
        super(message, throwable);

    }
}
