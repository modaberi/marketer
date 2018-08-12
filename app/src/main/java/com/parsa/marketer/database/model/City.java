package com.parsa.marketer.database.model;

//@Entity(tableName = "request")
public class City {

//    @PrimaryKey
//    @ColumnInfo(ownerName = "id")
    public int id;

//    @ColumnInfo(ownerName = "user_name")
    public String name;

    public int ostanId;
    public int lat;
    public int lng;


    public City(int id, String name, int ostanId, int lat, int lng) {
        this.id = id;
        this.name = name;
        this.ostanId = ostanId;
        this.lat = lat;
        this.lng = lng;
    }
}
