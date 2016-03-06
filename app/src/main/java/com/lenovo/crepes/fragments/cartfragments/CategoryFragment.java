package com.lenovo.crepes.fragments.cartfragments;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.CartCategoryAdapter;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.CartCategory;
import com.lenovo.crepes.utils.LogUtils;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment {
    private final int CATEGORY=100;

    private PullToRefreshGridView category_pull_to_refresh_gridview;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        category_pull_to_refresh_gridview = (PullToRefreshGridView) view.findViewById(R.id.category_pull_to_refresh_gridview);
        category_pull_to_refresh_gridview.getRefreshableView().setNumColumns(3);
        MyHttpUtils.sendDataArray(Common.category,handler,new CartCategory(),CATEGORY);
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CATEGORY:
                    List<CartCategory> list= (List<CartCategory>) msg.obj;
                    LogUtils.e("AAAA",list.toString());
                    CartCategoryAdapter adapter=new CartCategoryAdapter(activity,list,R.layout.fragment_category_item);
                    category_pull_to_refresh_gridview.setAdapter(adapter);
                    break;
            }
        }
    };


}
