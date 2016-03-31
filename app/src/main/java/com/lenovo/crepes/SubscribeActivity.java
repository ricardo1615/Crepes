package com.lenovo.crepes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SubscribeActivity extends AppCompatActivity {
//http://v2.api.dmzj.com/UCenter/subscribe?letter=all&uid=100528939&page=0&sub_type=1&type=0
//type=0漫画
//type=1小说

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
    }
}
