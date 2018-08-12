package com.parsa.marketer;

import android.content.Context;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import Utils.UiUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends RxAppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainApplication.setContext(this);

        UiUtil.setStatusBarColor(MainApplication.getContext());

        //launcher
//        List<User> userList = AppDatabase.getDatabase(this).userDao().getUserList();

//        if (userList == null){
//            startActivity(new Intent(this, LoginRegisterActivity.class));
//        }else {
//            startActivity(new Intent(this, MainActivity.class));
//        }
    }


}
