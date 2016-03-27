package com.lenovo.crepes.fragments.cartfragments;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.SubjectListAdapter;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.SubjectList;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends BaseFragment {

    //    String url = "http://v2.api.dmzj.com/subject/0/0.json";
//    String urlmore = "http://v2.api.dmzj.com/subject/0/1.json";
    private List<SubjectList> lists;
    private SubjectListAdapter adapter;
    private int subjectPage;

    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        lists = new ArrayList<>();
        adapter = new SubjectListAdapter(activity, lists, R.layout.item_subject_list);

        PullToRefreshListView pullListSubject = (PullToRefreshListView) view.findViewById(R.id.pull_list_subject);
        pullListSubject.setMode(PullToRefreshBase.Mode.BOTH);
        pullListSubject.setAdapter(adapter);
        pullListSubject.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                subjectPage = 0;
                MyHttpUtils.sendDataArray(getUrl(), handler, new SubjectList(), 100);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                subjectPage++;
                MyHttpUtils.sendDataArray(getUrl(), handler, new SubjectList(), 100);
            }
        });

        pullListSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                http://v2.api.dmzj.com/subject/68.json
            }
        });

        MyHttpUtils.sendDataArray(getUrl(), handler, new SubjectList(), 100);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                if (lists != null && adapter != null) {
                    lists.addAll(((List<SubjectList>) msg.obj));
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };

    public String getUrl() {
        return Common.subjectUrlHead + "/0/" + subjectPage + Common.CartDetailUrlfoot;
    }
}
