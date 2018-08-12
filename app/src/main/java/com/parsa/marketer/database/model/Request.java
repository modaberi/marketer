package com.parsa.marketer.database.model;

import java.util.List;

//@Entity(tableName = "request")
public class Request {

//    @PrimaryKey
//    @ColumnInfo(ownerName = "id")
    public String requestNo;
    public int id;

//    @ColumnInfo(ownerName = "user_name")
    public City city;
    public String state;
    public String address;
    public String zipCode;
    public String ownerName;
    public String deviceName;
    public String deviceRent;
    public String ssn;
    public String phone;
    public EnumActivityType activityType;
    public EnumOwnershipType ownerType;
    public String description;
    public List<ItemImage> imageList;
    public String lastAcceptanceDate;
    public String nurseName;

    public Request(String requestNo, String state, String ownerName, String ssn, String lastAcceptanceDate, String nurseName) {
        this.requestNo = requestNo;
        this.state = state;
        this.ownerName = ownerName;
        this.ssn = ssn;
        this.lastAcceptanceDate = lastAcceptanceDate;
        this.nurseName = nurseName;
    }

    public Request(String requestNo, int id, City city, String state, String address, String zipCode, String name, String deviceName, String deviceRent, String ssn, String phone, EnumActivityType activityType, EnumOwnershipType ownerType, String description, List<ItemImage> imageList) {
        this.requestNo = requestNo;
        this.id = id;
        this.city = city;
        this.state = state;
        this.address = address;
        this.zipCode = zipCode;
        this.ownerName = name;
        this.deviceName = deviceName;
        this.deviceRent = deviceRent;
        this.ssn = ssn;
        this.phone = phone;
        this.activityType = activityType;
        this.ownerType = ownerType;
        this.description = description;
        this.imageList = imageList;
    }

    public enum EnumActivityType {
        TYPE_CULTURAL("TYPE_CULTURAL", 1);

        public String methodName;
        public int methodValue;

        EnumActivityType(String methodName, int methodValue) {
            this.methodName = methodName;
            this.methodValue = methodValue;
        }

    }

    public enum EnumOwnershipType {
        TYPE_PERSONAL("TYPE_PERSONAL", 1),

        TYPE_GOV("TYPE_GOV", 2),

        TYPE_COMPANY("TYPE_COMPANY", 3);

        public String methodName;
        public int methodValue;

        EnumOwnershipType(String methodName, int methodValue) {
            this.methodName = methodName;
            this.methodValue = methodValue;
        }

    }

    public Request() {
    }
}
