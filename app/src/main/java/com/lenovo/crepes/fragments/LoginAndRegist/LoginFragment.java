package com.lenovo.crepes.fragments.LoginAndRegist;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.crepes.R;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.LoginResult;
import com.lenovo.crepes.utils.MyHttpUtils;
import com.lidroid.xutils.http.RequestParams;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {


    private static final int LOGIN = 100;
    private EditText et_user_name;
    private EditText et_user_password;
    private SharedPreferences preferences;
    private ProgressDialog dialog;
    private String userPwd;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_login, container, false);

        preferences = activity.getSharedPreferences("loginData", Context.MODE_PRIVATE);

        initView(inflate);

        return inflate;
    }

    private void initView(View inflate) {
        et_user_name = (EditText) inflate.findViewById(R.id.et_user_name);
        et_user_password = (EditText) inflate.findViewById(R.id.et_user_password);

        et_user_name.setText(preferences.getString("userName", ""));

        inflate.findViewById(R.id.tv_regist).setOnClickListener(this);
        inflate.findViewById(R.id.btn_login).setOnClickListener(this);
        inflate.findViewById(R.id.iv_login_weibo).setOnClickListener(this);
        inflate.findViewById(R.id.iv_login_qq).setOnClickListener(this);
        inflate.findViewById(R.id.iv_login_weixin).setOnClickListener(this);
        inflate.findViewById(R.id.login_back).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_regist://注册
                getFragmentManager().beginTransaction().replace(R.id.rl_login_replace,new RegistFragment()).commit();
                break;
            case R.id.btn_login://登录
                String userName = et_user_name.getText().toString();
                userPwd = et_user_password.getText().toString();
                RequestParams params = new RequestParams();
                params.addBodyParameter("nickname", userName);
                params.addBodyParameter("passwd", userPwd);
                if (userName.isEmpty() || userPwd.isEmpty()) {
                    Toast.makeText(activity, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    MyHttpUtils.sendDataOfPost(Common.loginUrl, params, handler, new LoginResult(), LOGIN);
                    dialog = new ProgressDialog(activity);
                    dialog.setMessage("登录中，请稍后...");
                    dialog.show();
                }
                break;
            case R.id.iv_login_weibo://微博登录
                break;
            case R.id.iv_login_qq://QQ登录
                break;
            case R.id.iv_login_weixin://微信登录
                break;
            case R.id.login_back://返回
                activity.finish();
                break;
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

                            SharedPreferences.Editor edit = preferences.edit();
                            edit.putString("userName", data.getNickname());
                            edit.putString("userPwd", userPwd);
                            edit.apply();

                            MyApp.setUserData(data);
                            dialog.dismiss();
                            activity.finish();
                        } else {
                            Toast.makeText(activity, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
        }
    };
}
