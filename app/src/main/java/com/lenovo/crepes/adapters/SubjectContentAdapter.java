package com.lenovo.crepes.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.entities.SubjectDetail;

import java.util.List;

/**
 * Created by Administrator on 2016/3/28.
 */
public class SubjectContentAdapter extends CustomerAdapter<SubjectDetail.ComicsEntity> implements View.OnClickListener {
    private Context context;

    public SubjectContentAdapter(Context context, List<SubjectDetail.ComicsEntity> list, int resId) {
        super(context, list, resId);
        this.context = context;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        SubjectDetail.ComicsEntity comicsEntity = list.get(position);
        ImageView ivComicCover = (ImageView) viewHolder.findView(R.id.iv_comic_cover);
        ivComicCover.setTag(comicsEntity.getCover());
        new ImageAsyncTask(ivComicCover,4).execute(comicsEntity.getCover());

        ((TextView) viewHolder.findView(R.id.iv_comic_name)).setText(comicsEntity.getName());
        ((TextView) viewHolder.findView(R.id.iv_comic_brief)).setText(comicsEntity.getRecommend_brief());
        ((TextView) viewHolder.findView(R.id.iv_comic_reason)).setText(comicsEntity.getRecommend_reason());

        viewHolder.findView(R.id.tv_subject_subscribe).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
