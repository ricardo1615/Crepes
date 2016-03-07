package com.lenovo.crepes.fragments.LoginAndRegist;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.crepes.R;
import com.lenovo.crepes.base.BaseFragment;
import com.lidroid.xutils.http.RequestParams;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注册界面
 */
public class RegistFragment extends BaseFragment implements View.OnClickListener {


    private EditText ed_regist_name;
    private EditText ed_regist_email;
    private EditText ed_regist_password;
    private EditText ed_regist_password_again;

    public RegistFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regist, container, false);

        //初始化
        initView(view);

        return view;
    }

    private void initView(View view) {
        ed_regist_name = (EditText) view.findViewById(R.id.ed_regist_name);
        ed_regist_email = (EditText) view.findViewById(R.id.ed_regist_email);
        ed_regist_password = (EditText) view.findViewById(R.id.ed_regist_password);
        ed_regist_password_again = (EditText) view.findViewById(R.id.ed_regist_password_again);
        view.findViewById(R.id.regist_back).setOnClickListener(this);
        view.findViewById(R.id.btn_regist).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_back://返回按钮
                getFragmentManager().beginTransaction().replace(R.id.rl_login_replace, new LoginFragment()).commit();
                break;
            case R.id.btn_regist://注册按钮
                String name = ed_regist_name.getText().toString();
                String email = ed_regist_email.getText().toString();
                String password = ed_regist_password.getText().toString();
                String password_again = ed_regist_password_again.getText().toString();
                if (checkRegistMessage(name, email, password, password_again)) {
                    RequestParams params = new RequestParams();
//                    MyHttpUtils.sendDataOfPost(Common.registUrl,params,handler,null,100);
                    Toast.makeText(activity, "姑且算注册了吧", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    /**
     * 校验用户注册信息
     */
    private boolean checkRegistMessage(String name, String email, String password, String password_again) {
        if ("".equals(name)) {
            Toast.makeText(activity, "用户名不能为空", Toast.LENGTH_LONG).show();
            return false;
        }
        if ("".equals(email)) {
            Toast.makeText(activity, "邮箱不能为空", Toast.LENGTH_LONG).show();
            return false;
        } else {
            Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
            Matcher m = p.matcher(email);
            if (!m.matches()) {
                Toast.makeText(activity, "邮箱格式不正确", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if ("".equals(password)) {
            Toast.makeText(activity, "密码不能为空", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!password.equals(password_again)) {
            Toast.makeText(activity, "密码不一致", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (100 == msg.what) {//注册返回信息

            }
        }
    };
}
