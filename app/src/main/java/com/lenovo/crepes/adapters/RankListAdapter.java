package com.lenovo.crepes.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.entities.RankList;
import com.lenovo.crepes.utils.DataTransUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class RankListAdapter extends CustomerAdapter<RankList> {
    private List<RankList> list;
    private Context context;

    public RankListAdapter(Context context, List<RankList> list, int resId) {
        super(context, list, resId);
        this.context = context;
        this.list = list;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        RankList rank = list.get(position);
        ImageView ivComicCover = (ImageView) viewHolder.findView(R.id.iv_comic_cover);
        ivComicCover.setTag(rank.getCover());
        new ImageAsyncTask(ivComicCover, 4).execute(rank.getCover());
        ((TextView) viewHolder.findView(R.id.tv_comic_name)).setText(rank.getTitle());
        ((TextView) viewHolder.findView(R.id.tv_comic_author)).setText(rank.getAuthors());
        ((TextView) viewHolder.findView(R.id.tv_comic_tag)).setText(rank.getTypes());
        ((TextView) viewHolder.findView(R.id.tv_comic_clock)).setText(DataTransUtils.transdata(rank.getLast_updatetime() * 1000L, "yyyy-MM-dd"));
    }
}
