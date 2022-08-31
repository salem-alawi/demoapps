package com.deraah.demo_api.exceptions;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExceptionError {

    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    BAD_REQUEST_PARAMETER_MISSING("BAD_REQUEST_PARAMETER_MISSING"),
    BAD_REQUEST_DATA_BAD_FORMAT("BAD_REQUEST_DATA_BAD_FORMAT") ;
    public final String name;


    ExceptionError(String i) {
        name = i;
    }


    @JsonValue
    public String toValue() {
        return this.name;
    }

}
