package com.lenovo.crepes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.crepes.CartCategoryActivity;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.entities.CartCategory;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/18.
 */
public class CartCategoryAdapter extends CustomerAdapter<CartCategory> implements View.OnClickListener {
    private Context context;

    public CartCategoryAdapter(Context context, List<CartCategory> list, int resId) {
        super(context, list, resId);
        this.context = context;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        View view = viewHolder.getView();
        CartCategory cartCategory = list.get(position);
        ImageView category_item_imageview = (ImageView) view.findViewById(R.id.category_item_imageview);
        TextView category_item_texeview = (TextView) view.findViewById(R.id.category_item_texeview);
//        Glide.with(context).load(cartCategory.getCover()).centerCrop().into(category_item_imageview);
//        MyApp.getMybitmapUtils().display(category_item_imageview, cartCategory.getCover(), new BitmapLoadCallBack<ImageView>() {
//            @Override
//            public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
//                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getWidth()*36/31, false);
//                Bitmap roundedCornerBitmap = PicUtil.getRoundedCornerBitmap(scaledBitmap, 14);
//                container.setImageBitmap(roundedCornerBitmap);
//                int width = bitmap.getWidth();
//                int height = bitmap.getHeight();
//                int width1 = container.getWidth();
//                float i = (width / (float) width1);
//                int i1 = (int) (width / i);
//                int i2 = (int) (height / i);
//                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, width1, i2, true);
//                ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
//                layoutParams.height = i2;
//                container.setLayoutParams(layoutParams);
//                container.setImageBitmap(bitmap1);
//            }
//
//            @Override
//            public void onLoadFailed(ImageView container, String uri, Drawable drawable) {
//
//            }
//        });
        new ImageAsyncTask(category_item_imageview,3).execute(cartCategory.getCover());
        category_item_texeview.setText(cartCategory.getTitle());
        DataTrans dataTrans = new DataTrans();
        dataTrans.name =cartCategory.getTitle();
        dataTrans.id =cartCategory.getTag_id();
        category_item_imageview.setTag(dataTrans);
        category_item_imageview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DataTrans dataTrans = (DataTrans) v.getTag();
        Toast.makeText(context, "id=" + dataTrans.id, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, CartCategoryActivity.class);
        intent.putExtra("id", dataTrans.id);
        intent.putExtra("name", dataTrans.name);
        context.startActivity(intent);
    }

    private class DataTrans{
        private int id;
        private String name;
    }
}
