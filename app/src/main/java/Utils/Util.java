package Utils;

import android.content.Context;

import com.parsa.marketer.MainApplication;

public class Util {

    public static Context getNotNullContext(Context context) {
        return context != null ? context : MainApplication.getContext();
    }
}
