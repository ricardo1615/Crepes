package com.lenovo.crepes.utils.httpUtils;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.lenovo.crepes.utils.LogUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Lenovo on 2015/10/31.
 */
public class MyRequestCallBackOfArray extends RequestCallBack<String> {
    private Object object;
    private Handler handler;
    private int what;

    public MyRequestCallBackOfArray(Handler handler, Object object, int what) {
        this.handler = handler;
        this.object = object;
        this.what = what;
    }


    @Override
    public void onSuccess(ResponseInfo<String> responseInfo) {
        LogUtils.e("AAAA", responseInfo.result);
        try {
            List<Object> list=new LinkedList<Object>();
            JSONArray jsonArray = new JSONArray(responseInfo.result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Object o = new Gson().fromJson(jsonObject.toString(), object.getClass());
                list.add(o);
            }
            Message message = handler.obtainMessage();
            message.what=what;
            message.obj=list;
            handler.sendMessage(message);
            LogUtils.e("AAAA", "下载并解析成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(HttpException error, String msg) {
        LogUtils.e("AAAA",msg+error.getMessage());
    }
}
