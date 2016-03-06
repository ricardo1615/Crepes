package com.lenovo.crepes.adapters;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.entities.ClassifyFilter;
import com.lenovo.crepes.utils.LogUtils;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Lenovo on 2015/11/18.
 */
public class ClassifyFilterAdapter extends CustomerAdapter<ClassifyFilter.ItemsEntity> implements View.OnClickListener {
    public ClassifyFilterAdapter(Context context, List<ClassifyFilter.ItemsEntity> list, int resId) {
        super(context, list, resId);
    }

    public void setData( List<ClassifyFilter.ItemsEntity> list){
        this.list=list;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        View view=viewHolder.getView();
        Button category_classify_textview = (Button) view.findViewById(R.id.category_classify_textview);
        category_classify_textview.setText(list.get(position).getTag_name());
        category_classify_textview.setOnClickListener(this);
        category_classify_textview.setTag(list.get(position).getTag_id()+"");
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        EventBus.getDefault().post(tag);
        LogUtils.e("AAAA",tag);
    }
}
