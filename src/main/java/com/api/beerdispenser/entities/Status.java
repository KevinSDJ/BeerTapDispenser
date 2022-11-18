package com.api.beerdispenser.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    OPEN("OPEN"),
    CLOSED("CLOSED");
    private String status;
    Status(String s){status=s;}

    @JsonValue
    public String getStatus() {
        return status;
    }
}
