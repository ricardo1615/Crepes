package com.lenovo.crepes.fragments;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lenovo.crepes.NewsWebActivity;
import com.lenovo.crepes.R;
import com.lenovo.crepes.TypesNewsActivity;
import com.lenovo.crepes.adapters.NewsViewPagerAdapter;
import com.lenovo.crepes.adapters.news.FastNewsAdapter;
import com.lenovo.crepes.adapters.news.NormalNewsAdapter;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.base.ListViewForScrollView;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.FastNews;
import com.lenovo.crepes.entities.HeadNews;
import com.lenovo.crepes.entities.NormalNews;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener {
    private final String headUrl = "http://v2.api.dmzj.com/article/recommend/header.json";
    private final String newsUrl = "http://v2.api.dmzj.com/article/list/0/2/";
    private final String fastUrl = "http://v2.api.dmzj.com/message/list/";
    private final int HEADNEWS = 100;
    private final int NORMALNEWS = 300;
    private static final int FASTNEWS = 400;
    private final int CHANGEHEADNEWSIMAGE = 200;

    private int newsPage = 0;
    private int fastPage = 0;
    private boolean isNormalNews = true;
    private TextView tv_head_news_title;
    private List<HeadNews.DataEntity> headNewsList;
    private List<ImageView> headNewsImageList;
    private ViewPager vp_news_fragment_head;
    private List<NormalNews> normalNewsList;
    private List<FastNews> fastNewsList;
    private NormalNewsAdapter normalNewsAdapter;
    private PullToRefreshScrollView pullscroll_news_fragment;
    private ListViewForScrollView lv_vp_news_fragment;
    private FastNewsAdapter fastNewsAdapter;
    private ProgressDialog dialog;
    private ScrollView refreshableView;

    public NewsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_news, container, false);

        dialog = new ProgressDialog(activity);

        initView(inflate);

        return inflate;
    }

    private void initView(View inflate) {

        inflate.findViewById(R.id.news_kinds).setOnClickListener(this);

        //滑动控件
        pullscroll_news_fragment = (PullToRefreshScrollView) inflate.findViewById(R.id.pullscroll_news_fragment);
        pullscroll_news_fragment.setMode(PullToRefreshBase.Mode.BOTH);

        refreshableView = pullscroll_news_fragment.getRefreshableView();

        pullscroll_news_fragment.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                if (isNormalNews) {
                    newsPage = 0;
                    MyHttpUtils.sendDataArray(newsUrl + newsPage + ".json", handler, new NormalNews(), NORMALNEWS);
                    normalNewsList.clear();
                } else {
                    fastPage = 0;
                    MyHttpUtils.sendDataArray(fastUrl + fastPage + ".json", handler, new FastNews(), FASTNEWS);
                    fastNewsList.clear();
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                if (isNormalNews) {
                    newsPage++;
                    MyHttpUtils.sendDataArray(newsUrl + newsPage + ".json", handler, new NormalNews(), NORMALNEWS);
                } else {
                    fastPage++;
                    MyHttpUtils.sendDataArray(fastUrl + fastPage + ".json", handler, new FastNews(), FASTNEWS);
                }
            }
        });

        //头部轮播器控件
        vp_news_fragment_head = (ViewPager) inflate.findViewById(R.id.news_fragment_head).findViewById(R.id.vp_news_fragment_head);
        tv_head_news_title = (TextView) inflate.findViewById(R.id.news_fragment_head).findViewById(R.id.tv_head_news_title);

        //轮播器小圆点控件
        final RadioGroup rg_news_fragment_head = (RadioGroup) inflate.findViewById(R.id.news_fragment_head).findViewById(R.id.rg_news_fragment_head);

        //新闻、快讯切换按钮控件
        RadioGroup rg_news_type = (RadioGroup) inflate.findViewById(R.id.rg_news_type);

        //详细信息列表控件
        lv_vp_news_fragment = (ListViewForScrollView) inflate.findViewById(R.id.lv_vp_news_fragment);

        //新闻
        normalNewsList = new ArrayList<>();
        normalNewsAdapter = new NormalNewsAdapter(activity, normalNewsList, R.layout.item_normal_news);

        //初始化
        lv_vp_news_fragment.setAdapter(normalNewsAdapter);
        lv_vp_news_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, NewsWebActivity.class);
                intent.putExtra("newsId", normalNewsList.get(position).getArticle_id());
                intent.putExtra("webUrl", normalNewsList.get(position).getPage_url());
                startActivity(intent);
            }
        });

        //初始化轮播器
        rg_news_type.check(R.id.rb_news_fragment_news);
        rg_news_fragment_head.check(R.id.rb_head_one);

        //新闻、快讯切换
        rg_news_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (fastNewsList == null) {
                    dialog.show();
                    fastNewsList = new ArrayList<FastNews>();
                    fastNewsAdapter = new FastNewsAdapter(activity, fastNewsList, R.layout.item_fast_news);
                    lv_vp_news_fragment.setAdapter(fastNewsAdapter);
                    MyHttpUtils.sendDataArray(fastUrl + fastPage + ".json", handler, new FastNews(), FASTNEWS);
                }

                switch (checkedId) {
                    case R.id.rb_news_fragment_news:
                        lv_vp_news_fragment.setAdapter(normalNewsAdapter);

                        break;
                    case R.id.rb_news_fragment_fast:
                        lv_vp_news_fragment.setAdapter(fastNewsAdapter);
                        break;
                }
                isNormalNews = !isNormalNews;
            }
        });

        //联动
        vp_news_fragment_head.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg_news_fragment_head.check(Common.leads[position % 5]);
                tv_head_news_title.setText(headNewsList.get(position % 5).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //发请求
        dialog.show();
        MyHttpUtils.sendDataOfGet(headUrl, handler, new HeadNews(), HEADNEWS);
        MyHttpUtils.sendDataArray(newsUrl + newsPage + ".json", handler, new NormalNews(), NORMALNEWS);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HEADNEWS://头条新闻
                    HeadNews headNews = (HeadNews) msg.obj;
                    if (headNews != null) {
                        if (headNews.getCode() == 0 && "成功".equals(headNews.getMsg())) {
                            headNewsList = new ArrayList<>();
                            headNewsImageList = new ArrayList<>();
                            ImageView image;

                            //为数据源添加数据
                            headNewsList.addAll(headNews.getData());

                            //设置第一个标题
                            tv_head_news_title.setText(headNewsList.get(0).getTitle());
                            for (int i = 0; i < headNewsList.size(); i++) {
                                image = new ImageView(activity);
                                headNewsImageList.add(image);
//                                Glide.with(activity).load(headNewsList.get(i).getPic_url()).centerCrop().into(image);
                                image.setTag(i);
                                image.setOnClickListener(NewsFragment.this);
                                new ImageAsyncTask(image,2).execute(headNewsList.get(i).getPic_url());
                            }
                            NewsViewPagerAdapter adapter = new NewsViewPagerAdapter(headNewsImageList);
                            vp_news_fragment_head.setAdapter(adapter);
                            vp_news_fragment_head.setCurrentItem(1000);
                            handler.sendEmptyMessageDelayed(CHANGEHEADNEWSIMAGE, 5000);
                        } else {
                            //下载失败显示原因
                            Toast.makeText(activity, headNews.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    dialog.dismiss();
                    break;
                case CHANGEHEADNEWSIMAGE://轮播
                    vp_news_fragment_head.setCurrentItem(vp_news_fragment_head.getCurrentItem() + 1);
                    handler.sendEmptyMessageDelayed(CHANGEHEADNEWSIMAGE, 5000);
                    break;

                case NORMALNEWS://新闻列表
                    List<NormalNews> list1 = (List) msg.obj;
                    if (list1.size() > 0) {
                        normalNewsList.addAll(list1);
                        normalNewsAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(activity, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                    pullscroll_news_fragment.onRefreshComplete();
                    refreshableView.setSmoothScrollingEnabled(true);
                    dialog.dismiss();
                    break;
                case FASTNEWS://快讯列表
                    List<FastNews> list2 = (List) msg.obj;
                    if (list2.size() > 0) {
                        fastNewsList.addAll(list2);
                        fastNewsAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(activity, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                    pullscroll_news_fragment.onRefreshComplete();
                    refreshableView.setSmoothScrollingEnabled(true);
                    dialog.dismiss();
                    break;
            }
        }
    };

    /**
     * 各种点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.news_kinds:
                startActivity(new Intent(activity, TypesNewsActivity.class));
                break;
            default:
                int pos = ((int) v.getTag());
                Intent intent = new Intent(activity, NewsWebActivity.class);
                intent.putExtra("webUrl", headNewsList.get(pos).getObject_url());
                intent.putExtra("newsId", headNewsList.get(pos).getObject_id());
                startActivity(intent);
                break;
        }
    }
}
