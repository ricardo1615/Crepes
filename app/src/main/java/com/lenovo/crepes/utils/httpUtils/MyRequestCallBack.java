package com.lenovo.crepes.utils.httpUtils;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.lenovo.crepes.utils.LogUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;


/**
 * Created by Lenovo on 2015/10/31.
 */
public class MyRequestCallBack extends RequestCallBack<String> {
    private Object object;
    private Handler handler;
    private int what;

    public MyRequestCallBack(Handler handler, Object object, int what) {
        this.handler = handler;
        this.object = object;
        this.what = what;
    }


    @Override
    public void onSuccess(ResponseInfo<String> responseInfo) {
        LogUtils.e("AAAA", responseInfo.result);
        object = new Gson().fromJson(responseInfo.result, object.getClass());
        if (object!=null){
            Message message = handler.obtainMessage();
            message.what=what;
            message.obj=object;
            handler.sendMessage(message);
            LogUtils.e("AAAA","下载并解析成功");
        }
    }

    @Override
    public void onFailure(HttpException error, String msg) {
        LogUtils.e("AAAA",msg+error.getMessage());
    }
}
