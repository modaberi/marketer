package com.parsa.marketer.common;

public enum EnumSpinnerType {

    STATE("STATE", 1),
    CITY("CITY(", 2),
    ACTIVITY_TYPE("ACTIVITY_TYPE", 3),
    OWNERSHIP_TYPE("OWNERSHIP_TYPE", 4);

    public String methodName;
    public int methodValue;

    EnumSpinnerType(String methodName, int methodValue) {
        this.methodName = methodName;
        this.methodValue = methodValue;
    }


}
