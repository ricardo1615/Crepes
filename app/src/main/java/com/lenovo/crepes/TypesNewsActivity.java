package com.lenovo.crepes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lenovo.crepes.adapters.news.NormalNewsAdapter;
import com.lenovo.crepes.entities.NormalNews;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class TypesNewsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private PullToRefreshListView pulllist_types_news;
    private NormalNewsAdapter normalNewsAdapter;
    private List<NormalNews> newsList;
    private ProgressDialog dialog;
    private SlidingMenu menu;

    private int newsPage = 0;
    private StringBuffer newsType;
    private final int NORMALNEWS = 300;
    private final String newsUrl = "http://v2.api.dmzj.com/article/list/";
//    http://v2.api.dmzj.com/article/list/3_1_10_8/3/0.json

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types_news);
        dialog = new ProgressDialog(this);
        dialog.setMessage("这在加载请稍后");

        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.type_news_menu);

        menu.setOnCloseListener(new SlidingMenu.OnCloseListener() {
            @Override
            public void onClose() {
                if (((CheckBox) findViewById(R.id.rb_novel_msg)).isChecked()) {
                    newsType.append("3");
                }
                if (((CheckBox) findViewById(R.id.rb_anim_msg)).isChecked()) {
                    newsType.append("_1");
                }
                if (((CheckBox) findViewById(R.id.rb_something_msg)).isChecked()) {
                    newsType.append("_10");
                }
                if (((CheckBox) findViewById(R.id.rb_pic_msg)).isChecked()) {
                    newsType.append("_8");
                }
                if (((CheckBox) findViewById(R.id.rb_game_msg)).isChecked()) {
                    newsType.append("_7");
                }
                if (((CheckBox) findViewById(R.id.rb_comic_msg)).isChecked()) {
                    newsType.append("_2");
                }
                if (((CheckBox) findViewById(R.id.rb_other_msg)).isChecked()) {
                    newsType.append("_4");
                }
                if (((CheckBox) findViewById(R.id.rb_voice_msg)).isChecked()) {
                    newsType.append("_5");
                }
                if (((CheckBox) findViewById(R.id.rb_party_msg)).isChecked()) {
                    newsType.append("_9");
                }
                if (((CheckBox) findViewById(R.id.rb_music_msg)).isChecked()) {
                    newsType.append("_6");
                }
                dialog.show();
                if (newsType.length() > 0) {
                    if (newsType.indexOf("_") == 0) {
                        newsType.substring(1);
                    }
                } else {
                    newsType.append("0");
                }
                newsList.clear();
                MyHttpUtils.sendDataArray(newsUrl + newsType.toString() + "/3/" + newsPage + ".json", handler, new NormalNews(), NORMALNEWS);
                newsType.delete(0, newsType.length());
            }
        });

        newsType = new StringBuffer();
        pulllist_types_news = (PullToRefreshListView) findViewById(R.id.pulllist_types_news);
        newsList = new ArrayList<>();
        pulllist_types_news.setMode(PullToRefreshBase.Mode.BOTH);
        normalNewsAdapter = new NormalNewsAdapter(this, newsList, R.layout.item_normal_news);
        pulllist_types_news.setAdapter(normalNewsAdapter);

        pulllist_types_news.setOnItemClickListener(this);
        pulllist_types_news.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                newsPage = 0;
                newsList.clear();
                MyHttpUtils.sendDataArray(newsUrl + newsType + "/3/" + newsPage + ".json", handler, new NormalNews(), NORMALNEWS);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                newsPage++;
                MyHttpUtils.sendDataArray(newsUrl + newsType + "/3/" + newsPage + ".json", handler, new NormalNews(), NORMALNEWS);
            }
        });

        MyHttpUtils.sendDataArray(newsUrl + "0/3/" + newsPage + ".json", handler, new NormalNews(), NORMALNEWS);

        findViewById(R.id.types_news_menu).setOnClickListener(this);
        findViewById(R.id.types_news_back).setOnClickListener(this);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NORMALNEWS://新闻列表
                    List<NormalNews> list1 = (List) msg.obj;
                    if (list1.size() > 0) {
                        newsList.addAll(list1);
                        normalNewsAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(TypesNewsActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                    pulllist_types_news.onRefreshComplete();
                    dialog.dismiss();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.types_news_back:
                finish();
                break;
            case R.id.types_news_menu:
                menu.showMenu();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, NewsWebActivity.class);
        intent.putExtra("newsId", newsList.get(position - 1).getArticle_id());
        intent.putExtra("webUrl", newsList.get(position - 1).getPage_url());
        startActivity(intent);
    }
}
