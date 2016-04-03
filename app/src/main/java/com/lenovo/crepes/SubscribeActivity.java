package com.lenovo.crepes;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lenovo.crepes.adapters.SubscribeAdapter;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.entities.SubscribeComic;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class SubscribeActivity extends AppCompatActivity {
    //http://v2.api.dmzj.com/UCenter/subscribe?letter=all&uid=100528939&page=0&sub_type=1&type=0
//type=0漫画
//type=1小说
    private List<SubscribeComic> list;
    private SubscribeAdapter adapter;
    private int page = 0;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        init();
    }

    private void init() {
        PullToRefreshListView pullListSubscribe = (PullToRefreshListView) findViewById(R.id.pull_list_subscribe);
        list = new ArrayList<>();
        adapter = new SubscribeAdapter(this, list, R.layout.item_subscribe_comic);
        type = getIntent().getIntExtra("type", 0);

        pullListSubscribe.setMode(PullToRefreshBase.Mode.BOTH);
        pullListSubscribe.setAdapter(adapter);
        pullListSubscribe.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 0;
                MyHttpUtils.sendDataArray(getUrl(), handler, new SubscribeComic(), 100);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                MyHttpUtils.sendDataArray(getUrl(), handler, new SubscribeComic(), 100);
            }
        });
        pullListSubscribe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SubscribeActivity.this,CartDetailActivity.class);
                intent.putExtra("id",""+list.get(position-1).getId());
                startActivity(intent);
            }
        });
        MyHttpUtils.sendDataArray(getUrl(), handler, new SubscribeComic(), 100);
    }

    private String getUrl() {
        return "http://v2.api.dmzj.com/UCenter/subscribe?letter=all&uid=" + MyApp.getUserData().getUid() + "&page=" + page + "&sub_type=1&type=" + type;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    list.addAll((List<SubscribeComic>)msg.obj);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
