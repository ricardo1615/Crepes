package com.lenovo.crepes.utils.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.lenovo.crepes.app.MyApp;

import java.util.Map;


/**
 * Created by Lenovo on 2015/10/30.
 */
public class MyStringRequest extends StringRequest {
    private Map map;
    public MyStringRequest(int method, String url,  Object object,Map map) {
        super(method, url, new MyListener(object), new MyErrorListener());
        this.map=map;
        MyApp.getRequestQueue().add(this);
    }

    public MyStringRequest(String url,  Object object) {
        super(url,  new MyListener(object), new MyErrorListener());
        MyApp.getRequestQueue().add(this);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
