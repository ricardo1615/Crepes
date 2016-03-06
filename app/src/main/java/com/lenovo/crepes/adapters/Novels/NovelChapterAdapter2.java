package com.lenovo.crepes.adapters.Novels;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.entities.NovelChapter;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/20.
 */
public class NovelChapterAdapter2 extends CustomerAdapter<NovelChapter.ChaptersEntity> {
    private List<NovelChapter.ChaptersEntity> list;

    public NovelChapterAdapter2(Context context, List<NovelChapter.ChaptersEntity> list, int resId) {
        super(context, list, resId);
        this.list = list;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        View view = viewHolder.getView();
        String name = "";
        if (list.get(position).getChapter_id() == -1) {
            name = list.get(position).getP_volume_name();
        } else {
            name = list.get(position).getChapter_name();
        }
        ((TextView) view.findViewById(R.id.iv_novel_chapter_name)).setText(name);
    }
}
