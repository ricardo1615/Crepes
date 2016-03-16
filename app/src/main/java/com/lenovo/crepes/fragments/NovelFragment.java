package com.lenovo.crepes.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.crepes.NovelDetailActivity;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.NewsViewPagerAdapter;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.Novelhomepager;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NovelFragment extends BaseFragment implements View.OnClickListener {
    private String Url = "http://v2.api.dmzj.com/novel/recommend.json";//首页请求地址
    private List<ImageView> headNewsImageList;
    private ViewPager vp_novel_fragment_head;
    private TextView tv_head_novel_title;
    private List<Novelhomepager> headNovelsList;
    private List<ImageView> headNovelsImageList;
    public final int HOMEPAGE = 110;
    public int FAILDOENLOAGE = 111;
    private final int CHANGEHEADNEWSIMAGE = 200;
    private View inflate;

    public NovelFragment() {
        // Required empty public constructoraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        inflate = inflater.inflate(R.layout.fragment_novel, container, false);
        initView();
        return inflate;
    }

    private void initView() {

        //最近更新更多
        inflate.findViewById(R.id.sw_novel).findViewById(R.id.more).setOnClickListener(this);

        //头部轮播器控件
        vp_novel_fragment_head = (ViewPager) inflate.findViewById(R.id.sw_novel).findViewById(R.id.include_head_novel).findViewById(R.id.vp_news_fragment_head);
        tv_head_novel_title = ((TextView) inflate.findViewById(R.id.sw_novel).findViewById(R.id.include_head_novel).findViewById(R.id.tv_head_news_title));
        //点
        final RadioGroup rg_novel_fragment_head = (RadioGroup) inflate.findViewById(R.id.sw_novel).findViewById(R.id.include_head_novel).findViewById(R.id.rg_news_fragment_head);

        //联动
        vp_novel_fragment_head.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg_novel_fragment_head.check(Common.leads[position % 5]);
                tv_head_novel_title.setText(headNovelsList.get(0).getData().get(position % 5).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //初始化轮播器
        rg_novel_fragment_head.check(R.id.rb_head_one);
        //请求数据
        MyHttpUtils.sendDataArray(Url, handler, new Novelhomepager(), HOMEPAGE);

    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HOMEPAGE://小说首页
                    headNovelsList = new ArrayList<>();
                    headNovelsImageList = new ArrayList<ImageView>();
                    headNovelsList = (List) (msg.obj);
                    ImageView image;
                    if (headNovelsList != null) {
                        Log.e("Date==", headNovelsList.get(0).getData().get(0).getTitle());
                    } else {
                        Log.e("空", "------------------");
                    }
                    tv_head_novel_title.setText(headNovelsList.get(0).getData().get(0).getTitle());
                    for (int i = 0; i < headNovelsList.size(); i++) {
                        image = new ImageView(activity);
                        headNovelsImageList.add(image);
//                        Glide.with(activity).load(headNovelsList.get(0).getData().get(i).getCover()).into(image);
                        new ImageAsyncTask(image,3).execute(headNovelsList.get(0).getData().get(i).getCover());
                        image.setTag(i);
                        image.setOnClickListener(NovelFragment.this);
                    }
                    NewsViewPagerAdapter adapter = new NewsViewPagerAdapter(headNovelsImageList);
                    vp_novel_fragment_head.setAdapter(adapter);
                    vp_novel_fragment_head.setCurrentItem(1000);
                    handler.sendEmptyMessageDelayed(CHANGEHEADNEWSIMAGE, 5000);

                    novelUpdate();
                    break;
                case CHANGEHEADNEWSIMAGE://轮播
                    vp_novel_fragment_head.setCurrentItem(vp_novel_fragment_head.getCurrentItem() + 1);
                    handler.sendEmptyMessageDelayed(CHANGEHEADNEWSIMAGE, 5000);
                    break;
            }
        }
    };

    private void novelUpdate() {
        int[] viewId1 = {R.id.refresh1, R.id.refresh2, R.id.refresh3};
        List<Novelhomepager.DataEntity> data1 = headNovelsList.get(1).getData();
        for (int i = 0; i < data1.size(); i++) {
            ImageView book_cover = (ImageView) inflate.findViewById(R.id.sw_novel).findViewById(viewId1[i]).findViewById(R.id.book_cover);
            ((TextView) inflate.findViewById(R.id.sw_novel).findViewById(viewId1[i]).findViewById(R.id.book_name)).setText(data1.get(i).getTitle());
            (inflate.findViewById(R.id.sw_novel).findViewById(viewId1[i]).findViewById(R.id.book_author)).setVisibility(View.GONE);
            Glide.with(activity).load(data1.get(i).getCover()).centerCrop().into(book_cover);
            book_cover.setTag(data1.get(i).getObj_id());
            book_cover.setOnClickListener(this);
        }

        int[] viewId2 = {R.id.nowplaying1, R.id.nowplaying2, R.id.nowplaying3};
        int[] viewId3 = {R.id.willplay1, R.id.willplay2, R.id.willplay3};
        int[] viewId4 = {R.id.classic1, R.id.classic2, R.id.classic3, R.id.classic4, R.id.classic5, R.id.classic6};
        List<int[]> list = new ArrayList();
        list.add(viewId2);
        list.add(viewId3);
        list.add(viewId4);

        for (int i = 0; i < list.size(); i++) {
            List<Novelhomepager.DataEntity> data = headNovelsList.get(i + 2).getData();
            for (int j = 0; j < data.size(); j++) {
                ImageView book_cover = (ImageView) inflate.findViewById(R.id.sw_novel).findViewById(list.get(i)[j]).findViewById(R.id.book_cover);
                ((TextView) inflate.findViewById(R.id.sw_novel).findViewById(list.get(i)[j]).findViewById(R.id.book_name)).setText(data.get(j).getTitle());
                ((TextView) inflate.findViewById(R.id.sw_novel).findViewById(list.get(i)[j]).findViewById(R.id.book_author)).setText(data.get(j).getSub_title());
                Glide.with(activity).load(data.get(j).getCover()).centerCrop().into(book_cover);
                book_cover.setTag(data.get(j).getObj_id());
                book_cover.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more://最近更新更多

                break;

            case R.id.book_cover://点击进入详情
                int id = (int) v.getTag();
                Intent intent = new Intent(activity, NovelDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }
    }
}




