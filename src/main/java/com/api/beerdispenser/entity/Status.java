package com.api.beerdispenser.entity;

import java.util.stream.Stream;

public enum Status {
    OPEN("open"),CLOSE("close");
    private String value;
    Status(String value) {
        this.value=value;
    }
    public String getValue(){
        return value;
    }
    public static Boolean containValue(String value){
        return Stream.of(values()).anyMatch(e-> e.name().equalsIgnoreCase(value));
    }
    public static String getValue(String value){
        return valueOf(value.toUpperCase()).getValue();
    }
}
