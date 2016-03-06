package com.lenovo.crepes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.LoginResult;
import com.lenovo.crepes.fragments.CartFragment;
import com.lenovo.crepes.fragments.MineFragment;
import com.lenovo.crepes.fragments.NewsFragment;
import com.lenovo.crepes.fragments.NovelFragment;
import com.lenovo.crepes.utils.MyHttpUtils;
import com.lidroid.xutils.http.RequestParams;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final int LOGIN = 100;
    private RadioGroup radioGroup;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("loginData", Context.MODE_PRIVATE);

        String userName = preferences.getString("userName", "");
        String userPwd = preferences.getString("userPwd", "");
        if (!"".equals(userPwd)) {
            RequestParams params = new RequestParams();
            params.addBodyParameter("nickname", userName);
            params.addBodyParameter("passwd", userPwd);
            MyHttpUtils.sendDataOfPost(Common.loginUrl, params, handler, new LoginResult(), LOGIN);
        }

        supportFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                hindAll();
                switch (checkedId) {
                    case R.id.cart:
                        init();
                        break;
                    case R.id.news:
                        NewsFragment newsFragment = (NewsFragment) supportFragmentManager.findFragmentByTag("NewsFragment");
                        if (null == newsFragment) {
                            newsFragment = new NewsFragment();
                            supportFragmentManager.beginTransaction().add(R.id.framelayout, newsFragment, "NewsFragment").commit();
                        } else {
                            supportFragmentManager.beginTransaction().show(newsFragment).commit();
                        }
                        break;
                    case R.id.novel:
                        NovelFragment novelFragment = (NovelFragment) supportFragmentManager.findFragmentByTag("NovelFragment");
                        if (null == novelFragment) {
                            novelFragment = new NovelFragment();
                            supportFragmentManager.beginTransaction().add(R.id.framelayout, novelFragment, "NovelFragment").commit();
                        } else {
                            supportFragmentManager.beginTransaction().show(novelFragment).commit();
                        }
                        break;
                    case R.id.mine:
                        MineFragment mineFragment = (MineFragment) supportFragmentManager.findFragmentByTag("MineFragment");
                        if (null == mineFragment) {
                            mineFragment = new MineFragment();
                            supportFragmentManager.beginTransaction().add(R.id.framelayout, mineFragment, "MineFragment").commit();
                        } else {
                            supportFragmentManager.beginTransaction().show(mineFragment).commit();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        init();
    }

    private void init() {
        CartFragment cartFragment = (CartFragment) supportFragmentManager.findFragmentByTag("CartFragment");
        if (null == cartFragment) {
            cartFragment = new CartFragment();
            supportFragmentManager.beginTransaction().add(R.id.framelayout, cartFragment, "CartFragment").commit();
        } else {
            supportFragmentManager.beginTransaction().show(cartFragment).commit();
        }
    }

    public void hindAll() {
        List<Fragment> fragments = supportFragmentManager.getFragments();
        if (null != fragments)
            for (int i = 0; i < fragments.size(); i++) {
                supportFragmentManager.beginTransaction().hide(fragments.get(i)).commit();
            }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOGIN:
                    LoginResult result = (LoginResult) msg.obj;
                    if (result != null) {
                        if ("OK".equals(result.getMsg())) {
                            LoginResult.DataEntity data = result.getData();
                            MyApp.setUserData(data);
                        } else {
                            Toast.makeText(MainActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
        }
    };
}
