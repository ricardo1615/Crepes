package com.lenovo.crepes.adapters.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/3.
 */
public abstract class CustomerAdapterOfRecycleView extends RecyclerView.Adapter<CustomerAdapterOfRecycleView.ViewHolder> {
    private Context context;

    private List data;
    private int resId;
    private MyOnclickListener myOnclickListener;
    private MyLongClickListener myLongClickListener;

    public void setMyLongClickListener(MyLongClickListener myLongClickListener) {
        this.myLongClickListener = myLongClickListener;
    }

    public void setMyOnclickListener(MyOnclickListener myOnclickListener) {
        this.myOnclickListener = myOnclickListener;
    }

    public CustomerAdapterOfRecycleView(Context context, List data, int resId) {
        this.context = context;
        this.data = data;
        this.resId = resId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resId, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        binds(holder, position);
    }

    public abstract void binds(ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (myOnclickListener != null) {
                int layoutPosition = getLayoutPosition();
                if (v != null) {
                    myOnclickListener.click(v, layoutPosition);
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (myLongClickListener != null) {
                myLongClickListener.onLongClick(v, getLayoutPosition());
            }
            return true;//不响应点击事件
        }
    }

    public interface MyOnclickListener {
        void click(View view, int position);
    }

    public interface MyLongClickListener {
        void onLongClick(View view, int position);
    }

}
