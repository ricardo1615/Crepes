package com.lenovo.crepes.utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.UMQQSsoHandler;

import java.util.Map;
import java.util.Set;

/**
 * Created by Lenovo on 2015/11/13.
 */
public class LoginUtils {

    /**
     * 基础登陆
     *
     * @param activity
     * @param mController
     * @param handler
     * @param what
     */
    private static void baselogin(SHARE_MEDIA share_media,final Activity activity, final UMSocialService mController, final Handler handler, final int what) {
        mController.doOauthVerify(activity, share_media, new SocializeListeners.UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                Toast.makeText(activity, "授权开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
                Toast.makeText(activity, "授权错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                Toast.makeText(activity, "授权完成", Toast.LENGTH_SHORT).show();
                //获取相关授权信息
                mController.getPlatformInfo(activity, SHARE_MEDIA.QQ, new SocializeListeners.UMDataListener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(activity, "获取平台数据开始...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(int status, Map<String, Object> info) {
                        if (status == 200 && info != null) {
                            StringBuilder sb = new StringBuilder();
                            Set<String> keys = info.keySet();
                            for (String key : keys) {
                                sb.append(key + "=" + info.get(key).toString() + "\r\n");
                            }
                            LogUtils.e("TestData", sb.toString());
                            sendMessage(sb, handler, what);
                        } else {
                            LogUtils.e("TestData", "发生错误：" + status);
                        }
                    }
                });
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(activity, "授权取消", Toast.LENGTH_SHORT).show();
            }

        });
    }

    /**
     * QQ登陆
     *
     * @param activity
     * @param mController
     * @param handler
     * @param what
     */
    public static void qqLogin(final Activity activity, final UMSocialService mController, final Handler handler, final int what) {
        //参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, "100424468",
                "c7394704798a158208a74ab60104f0ba");
        qqSsoHandler.addToSocialSDK();
        baselogin(SHARE_MEDIA.QQ,activity, mController, handler, what);
    }

    /**
     * 新浪微博登陆
     *
     * @param activity
     * @param mController
     * @param handler
     * @param what
     */
    public static void sinlaLogin(final Activity activity, final UMSocialService mController, final Handler handler, final int what) {
        baselogin(SHARE_MEDIA.SINA,activity, mController, handler, what);
    }

    /**
     * 微信登陆
     *
     * @param activity
     * @param mController
     * @param handler
     * @param what
     */
    public static void weixinLogin(final Activity activity, final UMSocialService mController, final Handler handler, final int what) {
        baselogin(SHARE_MEDIA.WEIXIN,activity, mController, handler, what);
    }
    /**
     * 豆瓣登陆
     *
     * @param activity
     * @param mController
     * @param handler
     * @param what
     */
    public static void doubanLogin(final Activity activity, final UMSocialService mController, final Handler handler, final int what) {
        baselogin(SHARE_MEDIA.DOUBAN,activity, mController, handler, what);
    }
    /**
     * 人人登陆
     *
     * @param activity
     * @param mController
     * @param handler
     * @param what
     */
    public static void renrenLogin(final Activity activity, final UMSocialService mController, final Handler handler, final int what) {
        baselogin(SHARE_MEDIA.RENREN,activity, mController, handler, what);
    }

    /**
     * 回传消息
     *
     * @param sb
     * @param handler
     * @param what
     */
    private static void sendMessage(StringBuilder sb, Handler handler, int what) {
        Message message = handler.obtainMessage();
        message.what = what;
        message.obj = sb.toString();
        handler.sendMessage(message);
    }

    public static void logout(final Activity activity, final UMSocialService mController) {
        mController.deleteOauth(activity, SHARE_MEDIA.SINA,
                new SocializeListeners.SocializeClientListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onComplete(int status, SocializeEntity entity) {
                        if (status == 200) {
                            Toast.makeText(activity, "删除成功.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, "删除失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
