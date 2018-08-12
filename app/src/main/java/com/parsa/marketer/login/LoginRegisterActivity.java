package com.parsa.marketer.login;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.parsa.marketer.AppDatabase;
import com.parsa.marketer.BaseActivity;
import com.parsa.marketer.R;
import com.parsa.marketer.database.model.User;

import java.util.List;

import Utils.FragmentUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class LoginRegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_register);
        FragmentUtil.replaceFragment(getSupportFragmentManager(), R.id.container_login_register,new LoginFragment(), LoginFragment.class.getName());

    }
}
