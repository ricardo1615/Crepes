package com.lenovo.crepes.app;

import android.app.Application;
import android.util.LruCache;
import android.view.Display;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lenovo.crepes.entities.LoginResult;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

/**
 * Created by Lenovo on 2015/11/10.
 */
public class MyApp extends Application {
    private static HttpUtils myhttpUtils;
    private static BitmapUtils mybitmapUtils;
    private static LoginResult.DataEntity userData;
//    // 首先在您的Activity中添加如下成员变量 分享
//    final static UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    public static HttpUtils getMyhttpUtils() {
        return myhttpUtils;
    }

    public static BitmapUtils getMybitmapUtils() {
        return mybitmapUtils;
    }

    public static LoginResult.DataEntity getUserData() {
        return userData;
    }

    public static void setUserData(LoginResult.DataEntity userData) {
        MyApp.userData = userData;
    }
    private static LruCache<String ,byte[]> lruCache;
    @Override
    public void onCreate() {
        super.onCreate();
        myhttpUtils = new HttpUtils(2 * 5000);
        mybitmapUtils = new BitmapUtils(this);


//        //分享控制
//        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        int maxsize= (int) (Runtime.getRuntime().totalMemory()/8);
         lruCache=new LruCache<>(maxsize);

    }

    public static LruCache<String, byte[]> getLruCache() {
        return lruCache;
    }

    private static Display defaultDisplay;//屏幕显示

    public static Display getDefaultDisplay() {
        return defaultDisplay;
    }

    public static void setDefaultDisplay(Display defaultDisplay) {
        MyApp.defaultDisplay = defaultDisplay;
    }

    public static RequestQueue requestQueue;

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

}
