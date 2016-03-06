package com.lenovo.crepes.adapters.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by Lenovo on 2015/10/27.
 */
public abstract class CustomerAdapter<T> extends BaseAdapter {
    public List<T> list;
    private Context context;
    private int resId;

    public CustomerAdapter(Context context, List<T> list,int resId) {
        this.context = context;
        this.list = list;
        this.resId=resId;
    }

    @Override
    public int getCount() {
        if (list != null) {
         return  list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=ViewHolder.getViewHolder(convertView,context,resId);
        fillData(viewHolder,position);
        return viewHolder.getView();
    }

    public abstract  void  fillData(ViewHolder viewHolder, int position);
}
