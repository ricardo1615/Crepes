package com.lenovo.crepes.fragments.cartfragments;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.RankListAdapter;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.RankList;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends BaseFragment {
    String url = "http://v2.api.dmzj.com/rank/0/0/0/0.json";
    String urlmore = "http://v2.api.dmzj.com/rank/0/0/0/1.json";
    private PullToRefreshListView pull_list_rank;
    private int rankPage=0;
    private List<RankList> lists;
    private RankListAdapter adapter;


    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        lists = new ArrayList<>();
        adapter = new RankListAdapter(activity,lists,R.layout.fragment_rank_list);

        pull_list_rank = (PullToRefreshListView) view.findViewById(R.id.pull_list_rank);
        pull_list_rank.setMode(PullToRefreshBase.Mode.BOTH);
        pull_list_rank.setAdapter(adapter);
        pull_list_rank.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                lists.clear();
                rankPage=0;
                MyHttpUtils.sendDataArray(getUrl(),handler,new RankList(),100);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                rankPage++;
                MyHttpUtils.sendDataArray(getUrl(),handler,new RankList(),100);
            }
        });
        MyHttpUtils.sendDataArray(getUrl(),handler,new RankList(),100);
    }


    public String getUrl() {
        return Common.rankUrlHead+"/0/0/0/"+rankPage+".json";
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                    pull_list_rank.onRefreshComplete();
                    if (lists != null) {
                        lists.addAll((List<RankList>) msg.obj);
                        adapter.notifyDataSetChanged();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
