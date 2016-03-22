package com.lenovo.crepes.adapters;

import android.content.Context;

import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.entities.RankList;

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

    }
}
