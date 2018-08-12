package com.parsa.marketer.database.model;

//@Entity(tableName = "user")
public class User {

//    @PrimaryKey
//    @ColumnInfo(name = "id")
    public int id;

//    @ColumnInfo(name = "user_name")
    public String userName;

    public String email;
    public String password;
    public String mobile;
    public String ssn;

    public User(int id, String userName, String email, String password, String mobile, String ssn) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.ssn = ssn;
    }

}
