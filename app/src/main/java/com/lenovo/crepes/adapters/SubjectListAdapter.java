package com.lenovo.crepes.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.SubjectList;
import com.lenovo.crepes.utils.DataTransUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public class SubjectListAdapter extends CustomerAdapter<SubjectList> {
    private Context context;
    private List<SubjectList> list;

    public SubjectListAdapter(Context context, List<SubjectList> list, int resId) {
        super(context, list, resId);
        this.context = context;
        this.list = list;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        SubjectList subjectList = list.get(position);
        ImageView iv_subject_cover = (ImageView) viewHolder.findView(R.id.iv_subject_cover);
        iv_subject_cover.setTag(subjectList.getSmall_cover());
        new ImageAsyncTask(iv_subject_cover, 4).execute(subjectList.getSmall_cover());
        ((TextView) viewHolder.findView(R.id.tv_subject_name)).setText(subjectList.getTitle());
        ((TextView) viewHolder.findView(R.id.tv_subject_date)).setText(DataTransUtils.transdata(subjectList.getCreate_time() * 1000L, Common.dateFormat));
    }
}
