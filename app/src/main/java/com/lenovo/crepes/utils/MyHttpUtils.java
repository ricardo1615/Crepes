package com.lenovo.crepes.utils;

import android.os.Environment;
import android.os.Handler;
import android.util.LruCache;
import android.widget.Toast;

import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.utils.httpUtils.MyRequestCallBack;
import com.lenovo.crepes.utils.httpUtils.MyRequestCallBackOfArray;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Lenovo on 2015/10/31.
 */
public class MyHttpUtils {

    public static void sendData(HttpRequest.HttpMethod method, String url, RequestParams params, Handler handler, Object object, int what) {
        if (NetworkUtils.isNetworkAvailable(MyApp.getInstance())) {
            if (HttpRequest.HttpMethod.POST.equals(method)) {
                MyApp.getMyhttpUtils().send(HttpRequest.HttpMethod.POST, url, params, new MyRequestCallBack(handler, object, what));
            } else {
                MyApp.getMyhttpUtils().send(HttpRequest.HttpMethod.GET, url, new MyRequestCallBack(handler, object, what));
            }
        } else {
            Toast.makeText(MyApp.getInstance(), "没网我也没办法", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * get请求
     *
     * @param url
     */
    public static void sendDataOfGet(String url, Handler handler, Object object, int what) {
        sendData(HttpRequest.HttpMethod.GET, url, null, handler, object, what);
    }

    public static void sendDataOfPost(String url, RequestParams params, Handler handler, Object object, int what) {
        sendData(HttpRequest.HttpMethod.POST, url, params, handler, object, what);
    }


    public static void sendDataArray(String url, final Handler handler, final Object object, final int what) {
        if (NetworkUtils.isNetworkAvailable(MyApp.getInstance())) {
            MyApp.getMyhttpUtils().send(HttpRequest.HttpMethod.GET, url, new MyRequestCallBackOfArray(handler, object, what));
        } else {
            Toast.makeText(MyApp.getInstance(), "没网我也没办法", Toast.LENGTH_SHORT).show();
        }
    }

//    public static HttpHandler DownData(HttpRequest.HttpMethod method, String url, String target, RequestParams params, boolean autoresume, boolean autoRename, RequestCallBack callBack){
//        if (HttpRequest.HttpMethod.POST.equals(method)){
//            return MyApp.getMyhttpUtils().download(method, url, target, params, autoresume, autoRename, callBack);
//        }else {
//            return MyApp.getMyhttpUtils().download(url,target,params,autoresume,autoRename,callBack);
//        }
//    }
//    public static HttpHandler DownDataOfGet(String url, String target){
//        return DownData(null,url,target,null,true,true,new DownRequestCallBack());
//    }
//    public static HttpHandler DownDataOfPost(String url, String target, RequestParams params){
//        return DownData(HttpRequest.HttpMethod.POST,url,target,params,false,false,new DownRequestCallBack());
//    }

    public static byte[] getBitMapFromURL(String url) {
        LruCache<String, byte[]> lruCache = MyApp.getLruCache();
        byte[] bitmap = lruCache.get(url);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), SecurityofMD5Utils.getMD5(url) + ".jpg");
        //从内存中查找
        if (null != bitmap) {
            LogUtils.e("AAAA", "内存");
            return bitmap;
        } else if (file.exists()) {//从磁盘中查找
            FileInputStream fileInputStream = null;
            LogUtils.e("AAAA", "磁盘");
            try {
                fileInputStream = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = fileInputStream.read(bytes)) != -1) {
                    baos.write(bytes, 0, length);
                }
                byte[] bytes1 = baos.toByteArray();
                lruCache.put(url, bytes1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {//从网络上下载
            FileOutputStream fileOutputStream = null;
            LogUtils.e("AAAA", "网络");
            try {
                URL url1 = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
                urlConnection.setRequestProperty("Referer", url);
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = urlConnection.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    fileOutputStream = new FileOutputStream(file);
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    while ((length = inputStream.read(bytes)) != -1) {
                        baos.write(bytes, 0, length);
                        fileOutputStream.write(bytes, 0, length);
                    }
                    fileOutputStream.flush();
                    byte[] bytes1 = baos.toByteArray();
                    lruCache.put(url, bytes1);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return lruCache.get(url);
    }


}
