package Utils;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public static List<String> getStateList(){
        List<String> stringList = new ArrayList<>();
        stringList.add("تهران");
        stringList.add("فارس");
        stringList.add("آذربایجان شرقس");
        stringList.add("اردبیل");
        stringList.add("گیلان");
        stringList.add("مازندران");

        return stringList;
    }

    public static List<String> getCityList(String state){
        List<String> stringList = new ArrayList<>();
        stringList.add("تهران");
        stringList.add("شهرری");
        stringList.add("ورامین");
        stringList.add("فیروزکوه");
        stringList.add("رودهن");
        return stringList;
    }

    public static List<String> getOwnershipTypeList(){

        List<String> stringList = new ArrayList<>();
        stringList.add("شخصی");
        stringList.add("دولتی");
        stringList.add("سازمانی");

        return stringList;
    }

    public static List<String> getActivityTypeList(){

        List<String> stringList = new ArrayList<>();
        stringList.add("فرهنگی");

        return stringList;
    }
}
