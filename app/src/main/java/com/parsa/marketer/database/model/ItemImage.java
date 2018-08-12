package com.parsa.marketer.database.model;

//@Entity(tableName = "request")
public class ItemImage {

    //    @PrimaryKey
//    @ColumnInfo(name = "id")
    public int id;

    //    @ColumnInfo(name = "user_name")
    public String path;

    public int relatedItemId;
    public EnumImageType type;

    public enum EnumImageType {
        TYPE_REQUEST("TYPE_REQUEST", 1);

        public String methodName;
        public int methodValue;

        EnumImageType(String methodName, int methodValue) {
            this.methodName = methodName;
            this.methodValue = methodValue;
        }

    }
}
