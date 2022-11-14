package com.haha.ds;

public enum DBTypeEnum {

    MASTER("master"), SLAVER("slaver");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}