package com.lenovo.crepes.fragments.cartfragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lenovo.crepes.CartDetailActivity;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.UpdateOfCartAdapter;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.CartUpdate;
import com.lenovo.crepes.utils.LogUtils;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private final int UPDATE=100;

    private PullToRefreshListView update_pull_to_refresh_listview;
    private List<CartUpdate> list;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        update_pull_to_refresh_listview = (PullToRefreshListView) view.findViewById(R.id.update_pull_to_refresh_listview);
        MyHttpUtils.sendDataArray(Common.update,handler,new CartUpdate(),UPDATE);
        update_pull_to_refresh_listview.setOnItemClickListener(this);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE:
                    list = (List<CartUpdate>) msg.obj;
                    LogUtils.e("AAAA", list.toString());
                    UpdateOfCartAdapter adapter=new UpdateOfCartAdapter(activity, list,R.layout.fragment_update_item);
                    update_pull_to_refresh_listview.setAdapter(adapter);
                    break;
            }
        }
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(activity, CartDetailActivity.class);
        intent.putExtra("id",list.get(position-1).getId()+"");
//        Log.e("AAAA","id="+list.get(position-1).getId()+"position="+position);
        startActivity(intent);
    }
}
