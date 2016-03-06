package com.lenovo.crepes.adapters.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Lenovo on 2015/10/27.
 */
public class ViewHolder {
    private  View view;

    public  ViewHolder(Context parent, int resId){
        view= LayoutInflater.from(parent).inflate(resId,null);
        view.setTag(this);
    }
    public View getView() {
        return view;
    }

    public  static  ViewHolder getViewHolder(View convertView, Context parent, int resId){
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder = new ViewHolder(parent,resId);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return  viewHolder;
    }

    public  View findView(int id){
        return  view.findViewById(id);
    }
}
