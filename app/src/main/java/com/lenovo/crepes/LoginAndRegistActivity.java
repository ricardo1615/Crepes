package com.lenovo.crepes;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.crepes.fragments.LoginAndRegist.LoginFragment;

public class LoginAndRegistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_regist);

        FragmentManager supportFragmentManager = getSupportFragmentManager();

        supportFragmentManager.beginTransaction().replace(R.id.rl_login_replace, new LoginFragment()).commit();


    }
}
