package com.lenovo.crepes.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.lenovo.crepes.utils.MyHttpUtils;
import com.lenovo.crepes.utils.PicUtil;

/**
 * Created by Administrator on 2016/3/16.
 */
public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;
    private String url;
    private int imageType;

    private int CIRCLEIMAGE = 1;//圆形头像
    private int HEADNEWSIMAGE = 2;//轮播器图片

    public ImageAsyncTask() {
    }

    public ImageAsyncTask(ImageView imageView, int imageType) {
        this.imageView = imageView;
        this.imageType = imageType;
    }

    @Override
    protected void onPreExecute() {
        if (imageView != null) {
            imageView.setImageBitmap(null);
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        url = params[0];
        if (url != null) {
            byte[] bitMapFromURL = MyHttpUtils.getBitMapFromURL(url);
            if (bitMapFromURL != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitMapFromURL, 0, bitMapFromURL.length);
                return bitmap;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        switch (imageType) {
            case 0://复用性条目
            case 4://复用性条目,边角有弧度
            case 1://圆形头像
                if (bitmap != null && imageView != null) {
                    String tag = (String) imageView.getTag();
                    if (tag != null && url != null && tag.equals(url)) {
                        if (imageType == CIRCLEIMAGE) {
                            bitmap = PicUtil.getRoundedCornerBitmap(bitmap, 2);
                        }
                        if (imageType == 4) {
                            bitmap = PicUtil.getRoundedCornerBitmap(bitmap, 12);
                        }
                        imageView.setImageBitmap(bitmap);
                    }

                }
                break;
            case 2://轮播器图片
            case 3://漫画封面图片
                if (bitmap != null && imageView!=null) {
                    imageView.setImageBitmap(bitmap);
                }
                break;
        }

    }
}
