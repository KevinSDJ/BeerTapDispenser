package com.api.beerdispenser.entity;


public enum Status {
    OPEN(1,"open"),CLOSE(2,"close");

    private int key;
    private String value;
    Status(int key,String value){
        this.key=key;
        this.value=value;
    }

    public int getKey(){
        return this.key;
    }
    public String getValue(){
        return this.value;
    }

}
