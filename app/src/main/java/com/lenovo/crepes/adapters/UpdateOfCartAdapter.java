package com.lenovo.crepes.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.entities.CartUpdate;
import com.lenovo.crepes.utils.DataTransUtils;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/17.
 */
public class UpdateOfCartAdapter extends CustomerAdapter<CartUpdate> implements View.OnClickListener {
    private Context context;
    public UpdateOfCartAdapter(Context context, List<CartUpdate> list, int resId) {
        super(context, list, resId);
        this.context=context;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        View view = viewHolder.getView();
        final CartUpdate cartUpdate = list.get(position);
        ImageView update_item_imageview = (ImageView) view.findViewById(R.id.update_item_imageview);
        TextView update_item_title = (TextView) view.findViewById(R.id.update_item_title);
        TextView update_item_auther = (TextView) view.findViewById(R.id.update_item_auther);
        TextView update_item_type = (TextView) view.findViewById(R.id.update_item_type);
        TextView update_item_time = (TextView) view.findViewById(R.id.update_item_time);
        ImageView update_item_read = (ImageView) view.findViewById(R.id.update_item_read);
        TextView update_item_data = (TextView) view.findViewById(R.id.update_item_data);
        MyApp.getMybitmapUtils().display(update_item_imageview, cartUpdate.getCover());
        update_item_read.setTag(cartUpdate.getId()+"");
        update_item_read.setOnClickListener(this);
        update_item_title.setText(cartUpdate.getTitle());
        update_item_auther.setText(cartUpdate.getAuthors());
        update_item_auther.setText(cartUpdate.getAuthors());
        update_item_type.setText(cartUpdate.getTypes());
        update_item_time.setText(DataTransUtils.transdata(cartUpdate.getLast_updatetime()*1000l,"yyyy-MM-dd"));
        update_item_data.setText(cartUpdate.getLast_update_chapter_name());
    }

    /**
     * 开始阅读
     * @param v
     */
    @Override
    public void onClick(View v) {
        String id = (String) v.getTag();
//        Toast.makeText(context,"id="+ id,Toast.LENGTH_LONG).show();
    }
}
