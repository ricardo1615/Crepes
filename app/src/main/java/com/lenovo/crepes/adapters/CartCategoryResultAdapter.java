package com.lenovo.crepes.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.entities.CartCategoryResult;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/19.
 */
public class CartCategoryResultAdapter extends CustomerAdapter<CartCategoryResult> implements View.OnClickListener {
    private Context context;
    public CartCategoryResultAdapter(Context context, List<CartCategoryResult> list, int resId) {
        super(context, list, resId);
        this.context=context;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        View view = viewHolder.getView();
        CartCategoryResult cartCategoryResult = list.get(position);
        ImageView cart_item_imageview = (ImageView) view.findViewById(R.id.cart_item_imageview);
        TextView cart_item_title = (TextView) view.findViewById(R.id.cart_item_title);
        TextView cart_item_auther = (TextView) view.findViewById(R.id.cart_item_auther);
//        MyApp.getMybitmapUtils().display(cart_item_imageview,cartCategoryResult.getCover());
        new ImageAsyncTask(cart_item_imageview,3).execute(cartCategoryResult.getCover());
        cart_item_imageview.setTag(cartCategoryResult.getId()+"");
        cart_item_imageview.setOnClickListener(this);
        cart_item_title.setText(cartCategoryResult.getTitle());
        cart_item_auther.setText(cartCategoryResult.getAuthors());
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        Toast.makeText(context,"id="+tag,Toast.LENGTH_LONG).show();
    }
}
