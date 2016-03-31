package com.lenovo.crepes.fragments;


import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.crepes.LoginAndRegistActivity;
import com.lenovo.crepes.R;
import com.lenovo.crepes.SettingsActivity;
import com.lenovo.crepes.SubscribeActivity;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.LoginResult;
import com.lenovo.crepes.entities.SubscribeComic;
import com.lenovo.crepes.utils.GlideRoundTransform;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {


    private RadioGroup rg_mine_type;
    private String userPwd;
    private String userName;
    private ImageView iv_user_photo;
    private TextView tv_user_name;
    private TextView tv_mine_msg;
    private Button btn_login_exit;
    private AlertDialog dialog;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);

        initView(inflate);

        return inflate;
    }

    @Override
    public void onResume() {
        LoginResult.DataEntity userData = MyApp.getUserData();
        if (userData != null) {
            btn_login_exit.setText("退出登录");
            btn_login_exit.setBackgroundResource(R.drawable.exit_button);
            tv_user_name.setText(userData.getNickname());
            tv_mine_msg.setVisibility(View.GONE);
//            Glide.with(activity).load(userData.getPhoto()).transform(new GlideRoundTransform(activity, 50)).into(iv_user_photo);
            iv_user_photo.setTag(userData.getPhoto());
            new ImageAsyncTask(iv_user_photo,1).execute(userData.getPhoto());
        } else {
            if (View.GONE == tv_mine_msg.getVisibility()) {
                refresh();
            }
        }
        super.onResume();
    }


    private void initView(final View inflate) {

        iv_user_photo = (ImageView) inflate.findViewById(R.id.iv_user_photo);
        tv_user_name = (TextView) inflate.findViewById(R.id.tv_user_name);
        tv_mine_msg = (TextView) inflate.findViewById(R.id.tv_mine_msg);

        //为我的设置设置点击事件
        inflate.findViewById(R.id.iv_mine_setting).setOnClickListener(this);

        //我的类型切换
        rg_mine_type = (RadioGroup) inflate.findViewById(R.id.rg_mine_type);
        rg_mine_type.check(R.id.rb_mine_comic);
        rg_mine_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_mine_comic:
                        setSelectText("漫画");
                        break;
                    case R.id.rb_mine_novel:
                        setSelectText("小说");
                        break;
                }
            }

            private void setSelectText(String type) {
                ((TextView) inflate.findViewById(R.id.tv_mine_subscribe)).setText(type + "订阅");
                ((TextView) inflate.findViewById(R.id.tv_mine_download)).setText(type + "下载");
                ((TextView) inflate.findViewById(R.id.tv_mine_comment)).setText(type + "评论");
            }
        });

        //我的选项
        inflate.findViewById(R.id.ll_mine_comic).setOnClickListener(this);
        inflate.findViewById(R.id.ll_mine_history).setOnClickListener(this);
        inflate.findViewById(R.id.ll_mine_download).setOnClickListener(this);
        inflate.findViewById(R.id.ll_comic_comment).setOnClickListener(this);

        btn_login_exit = (Button) inflate.findViewById(R.id.btn_login_exit);
        btn_login_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mine_setting://设置
                startActivity(new Intent(activity,SettingsActivity.class));
                break;
            case R.id.ll_mine_comic://我的漫画/小说
                startActivity(new Intent(activity, SubscribeActivity.class));
                break;
            case R.id.ll_mine_history://我的浏览历史
                break;
            case R.id.ll_mine_download://我的下载
                break;
            case R.id.ll_comic_comment://我的评论
                break;
            case R.id.btn_login_exit://我的登录/注册/退出
                if ("退出登录".equals(btn_login_exit.getText())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    dialog = builder.setTitle("提示").setMessage("您请定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userExit();
                        }
                    }).create();
                    dialog.show();
                } else {
                    Intent intent = new Intent(activity, LoginAndRegistActivity.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                }
                break;
        }
    }

    private void userExit() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("uid", MyApp.getUserData().getUid());
        params.addBodyParameter("device", "android");
        MyApp.getMyhttpUtils().send(HttpRequest.HttpMethod.POST, Common.exitUrl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });

        MyApp.setUserData(null);
        activity.getSharedPreferences("loginData", Context.MODE_PRIVATE).edit().remove("userPwd").apply();
        refresh();
    }


    private void refresh() {
        btn_login_exit.setBackgroundResource(R.drawable.login_button);
        btn_login_exit.setText("登录/注册");
        tv_mine_msg.setVisibility(View.VISIBLE);
        tv_user_name.setText("请登录");
        iv_user_photo.setImageResource(R.mipmap.img_def_head);
        dialog.dismiss();
    }
}
