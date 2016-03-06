package com.lenovo.crepes.utils.Volley;

import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Lenovo on 2015/10/30.
 */
public class MyImageListener implements  ImageLoader.ImageListener {
    private ImageView imageView;

    public MyImageListener(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
        String tag = (String) imageView.getTag();
        if (tag!=null&&tag.equals(response.getRequestUrl())){
            imageView.setImageBitmap(response.getBitmap());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
            imageView.setImageBitmap(null);
    }
}
