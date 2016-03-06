package com.lenovo.crepes.utils.Volley;

import com.android.volley.Response;
import com.google.gson.Gson;

import de.greenrobot.event.EventBus;

/**
 * Created by Lenovo on 2015/10/30.
 */
public class MyListener implements Response.Listener<String> {
    private Object objects;

    public MyListener(Object objects) {
        this.objects = objects;
    }


    @Override
    public void onResponse(String response) {
    objects=new Gson().fromJson(response,objects.getClass());
        EventBus.getDefault().post(objects);
    }
}
