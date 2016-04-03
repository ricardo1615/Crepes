package com.lenovo.crepes.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.entities.SubscribeComic;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public class SubscribeAdapter extends CustomerAdapter<SubscribeComic> implements View.OnClickListener {
    private Context context;

    public SubscribeAdapter(Context context, List<SubscribeComic> list, int resId) {
        super(context, list, resId);
        this.context = context;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        SubscribeComic subscribeComic = list.get(position);
        ImageView ivCover = (ImageView) viewHolder.findView(R.id.iv_comic_cover);
        ivCover.setTag(subscribeComic.getSub_img());
        new ImageAsyncTask(ivCover,4).execute(subscribeComic.getSub_img());

        ((TextView) viewHolder.findView(R.id.tv_comic_name)).setText(subscribeComic.getName());
        if (subscribeComic.getSub_readed()!=0) {
            ((TextView) viewHolder.findView(R.id.tv_readed)).setText("看到："+subscribeComic.getSub_readed()+"话");
        }
        ((TextView) viewHolder.findView(R.id.tv_update_to)).setText("最新：" + subscribeComic.getSub_update());

        View view = viewHolder.findView(R.id.iv_subscribe_more);
        view.setTag(position);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.iv_subscribe_more){
            Toast.makeText(context,"iv_subscribe_more",Toast.LENGTH_SHORT).show();
        }
    }
}
