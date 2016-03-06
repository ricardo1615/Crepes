package com.lenovo.crepes.adapters.Novels;

import android.content.Context;
import android.widget.TextView;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.entities.NovelDetail;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/20.
 */
public class NovelchapterAdapter extends CustomerAdapter<NovelDetail.VolumeEntity> {
    private List<NovelDetail.VolumeEntity> list;

    public NovelchapterAdapter(Context context, List list, int resId) {
        super(context, list, resId);
        this.list = list;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        TextView tv_novel_chapter = (TextView) viewHolder.getView().findViewById(R.id.tv_novel_chapter);
        tv_novel_chapter.setText(list.get(position).getVolume_name());
    }
}
