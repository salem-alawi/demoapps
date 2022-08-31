package com.deraah.demo_api.exceptions;

public class IllegalOperatorException extends  Exception{

    public IllegalOperatorException() {
    }

    public IllegalOperatorException(String message) {
        super(message);
    }

    public IllegalOperatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalOperatorException(Throwable cause) {
        super(cause);
    }

    public IllegalOperatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
