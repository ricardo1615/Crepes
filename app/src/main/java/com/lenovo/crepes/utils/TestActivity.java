package com.lenovo.crepes.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.lenovo.crepes.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            imageView.setImageBitmap(bitmap);
        }
    };
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        imageView = (ImageView) findViewById(R.id.imageView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://imgsmall.dmzj.com/w/11901/22525/1.jpg");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Referer", "http://imgsmall.dmzj.com/w/11901/22525/1.jpg");
                    ByteArrayOutputStream out = new ByteArrayOutputStream();

                    InputStream inputStream = connection.getInputStream();
                    byte[] b = new byte[1024];
                    int leng = 0;
                    while ((leng = inputStream.read(b)) != -1) {
                        out.write(b, 0, leng);
                    }

                    Bitmap bitmap = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.toByteArray().length);
                    Message message = handler.obtainMessage();
                    message.obj = bitmap;
                    handler.sendMessage(message);

                    int responseCode = connection.getResponseCode();

                    Log.i("AAAA", responseCode + "");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();


    }
}
