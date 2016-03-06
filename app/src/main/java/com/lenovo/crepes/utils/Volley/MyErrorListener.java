package com.lenovo.crepes.utils.Volley;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Lenovo on 2015/10/30.
 */
public class MyErrorListener implements  Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("AAAA",error.getMessage());
    }
}
