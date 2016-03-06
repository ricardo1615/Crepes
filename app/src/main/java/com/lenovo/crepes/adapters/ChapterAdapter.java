package com.lenovo.crepes.adapters;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.entities.CartDetail;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Lenovo on 2015/11/19.
 */
public class ChapterAdapter extends CustomerAdapter<CartDetail.ChaptersEntity.DataEntity> implements View.OnClickListener {
    private Context context;
    public ChapterAdapter(Context context, List<CartDetail.ChaptersEntity.DataEntity> list, int resId) {
        super(context, list, resId);
        this.context=context;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        View view = viewHolder.getView();
        CartDetail.ChaptersEntity.DataEntity dataEntity = list.get(position);
        Button cartdetail_textview = (Button) view.findViewById(R.id.cartdetail_textview);
        cartdetail_textview.setText(dataEntity.getChapter_title());
        cartdetail_textview.setTag(dataEntity.getChapter_id() + "");
        cartdetail_textview.setOnClickListener(this);
    }
    public void setData(List<CartDetail.ChaptersEntity.DataEntity> list){
        super.list=list;
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (!"-1".equals(tag)){
//            Toast.makeText(context,"id:"+tag,Toast.LENGTH_LONG).show();
            EventBus.getDefault().post(new String[]{"read",tag});
        }else {
//            Toast.makeText(context,"id:"+tag,Toast.LENGTH_LONG).show();
            EventBus.getDefault().post(new String[]{"expand",tag});
        }
    }
}
