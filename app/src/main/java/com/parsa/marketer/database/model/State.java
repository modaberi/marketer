package com.parsa.marketer.database.model;

//@Entity(tableName = "request")
public class State {

//    @PrimaryKey
//    @ColumnInfo(ownerName = "id")
    public int id;

//    @ColumnInfo(ownerName = "user_name")
    public String name;

    public int lat;
    public int lng;


    public State(int id, String name, int lat, int lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }
}
