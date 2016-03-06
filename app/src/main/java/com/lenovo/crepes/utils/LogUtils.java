package com.lenovo.crepes.utils;

import android.util.Log;

/**
 * Created by Lenovo on 2015/10/31.
 */
public class LogUtils {
    private final static boolean ISDEBUG=true;
    public static void e(String TAG,String value){
        if (ISDEBUG) {
            Log.e(TAG,value);
        }
    }
}
