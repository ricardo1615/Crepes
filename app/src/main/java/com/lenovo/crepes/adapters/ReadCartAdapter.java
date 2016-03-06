package com.lenovo.crepes.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapterOfRecycleView;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/19.
 */
public class ReadCartAdapter extends CustomerAdapterOfRecycleView{
    private List<String> data;
    private Activity context;

    public ReadCartAdapter(Context context, List<String> data, int resId) {
        super(context, data, resId);
        this.data=data;
        this.context= (Activity) context;
    }

    public void setData(List<String> data){
        this.data=data;
    }

    @Override
    public void binds(ViewHolder holder, int position) {
        View itemView = holder.itemView;
//        Log.e("AAAA", "position" + position);
        ImageView read_item_imageview = (ImageView) itemView.findViewById(R.id.read_item_imageview);
        read_item_imageview.setImageBitmap(null);
//        MyApp.getMybitmapUtils().display(read_item_imageview, data.get(position));
//        MyApp.getImageLoader().get(data.get(position), new MyImageListener(read_item_imageview));
        read_item_imageview.setTag(data.get(position));
        new MyAsyncTask(read_item_imageview,data.get(position)).execute(data.get(position));
        read_item_imageview.setOnTouchListener(new View.OnTouchListener() {
            // 计算点击的次数
            private int count = 0;
            // 第一次点击的时间 long型
            private long firstClick = 0;
            // 最后一次点击的时间
            private long lastClick = 0;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 如果第二次点击 距离第一次点击时间过长 那么将第二次点击看为第一次点击
                    if (firstClick != 0 && System.currentTimeMillis() - firstClick > 500) {
                        count = 0;
                    }
                    count++;
                    if (count == 1) {
                        firstClick = System.currentTimeMillis();
                    } else if (count == 2) {
                        lastClick = System.currentTimeMillis();
                        // 两次点击小于500ms 也就是连续点击
                        if (lastClick - firstClick < 500) {
                            Log.v("Double", "Double");

                        }
                        clear();
                    }
                }

                return false;
            }

            // 清空状态

            private void clear() {
                count = 0;
                firstClick = 0;
                lastClick = 0;
            }
        });
    }

    private  class MyAsyncTask extends AsyncTask<String,Void,byte[]>{
        private ImageView read_item_imageview;
        private String s;
        public MyAsyncTask(ImageView read_item_imageview, String s) {
            this.read_item_imageview=read_item_imageview;
            this.s=s;
        }

        @Override
        protected byte[] doInBackground(String... params) {
            byte[] bitmap = MyHttpUtils.getBitMapFromURL(params[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(byte[] bitmaparray) {
            if (s.equals((String)read_item_imageview.getTag())){
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inJustDecodeBounds = true;//设置为仅编码模式,在这个模式下,不会加载实际的图片,只是获取元数据
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmaparray,0,bitmaparray.length,options);
                int outHeight = options.outHeight;//获取图片的原始实际高度
                int outWidth = options.outWidth;//获取图片的原始实际宽度
                int height = context.getWindowManager().getDefaultDisplay().getHeight();//获取屏幕的高度
                int width = context.getWindowManager().getDefaultDisplay().getWidth();//获取屏幕的宽度
//                //分别计算宽度应缩小多少和高应该缩小多少,然后谁的值大 按照谁的来,防止一边进来了 另外一边还在屏幕外面
                float x=  (outWidth/(float)width);//强转成 float 算出一个小数,按照低于1.3就不再缩放的准则,加0.7f 之后再强转回 int
                int v =(int)( outHeight / x);

                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                options.inInputShareable = true;
                options.inJustDecodeBounds=false;//退出仅编码模式
                Bitmap newBitmap = BitmapFactory.decodeByteArray(bitmaparray,0,bitmaparray.length,options);//重新生成图片
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(newBitmap, width, v, true);
                read_item_imageview.setImageBitmap(scaledBitmap);
            }
        }
    }

}
