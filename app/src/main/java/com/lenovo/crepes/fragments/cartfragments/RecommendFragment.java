package com.lenovo.crepes.fragments.cartfragments;


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
import android.widget.Toast;

import com.lenovo.crepes.CartDetailActivity;
import com.lenovo.crepes.CartWebActivity;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.CartRecommendViewPagerAdapter;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.CartRecommend;
import com.lenovo.crepes.entities.TellMeWhy;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseFragment implements View.OnClickListener {
    private final int HEADCARTS = 100;
    private final int YOURLIKE = 200;
    private final int CHANGEHEADNEWSIMAGE = 300;


    private TextView tv_head_news_title;
    private RadioGroup rg_news_fragment_head;
    private List<CartRecommend> recommends;
    private ViewPager vp_news_fragment_head;
    private View view;
    private Intent intent;

    public RecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initView();
        return view;
    }

    private void initView() {
        //头部轮播器控件
        vp_news_fragment_head = (ViewPager) view.findViewById(R.id.carts_fragment_head).findViewById(R.id.vp_news_fragment_head);
        tv_head_news_title = (TextView) view.findViewById(R.id.carts_fragment_head).findViewById(R.id.tv_head_news_title);

        //轮播器小圆点控件
        rg_news_fragment_head = (RadioGroup) view.findViewById(R.id.carts_fragment_head).findViewById(R.id.rg_news_fragment_head);
        //联动
        vp_news_fragment_head.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg_news_fragment_head.check(Common.leads[position % 5]);
                tv_head_news_title.setText(recommends.get(0).getData().get(position % 5).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //请求数据
        MyHttpUtils.sendDataArray(Common.recommend, handler, new CartRecommend(), HEADCARTS);
        //猜你喜欢
        MyHttpUtils.sendDataOfGet(Common.tellmowhy, handler, new TellMeWhy(), YOURLIKE);

        ImageView cart_huorezhuanti_btn = (ImageView) view.findViewById(R.id.cart_huorezhuanti_btn);
        ImageView cart_caininxihuan_btn = (ImageView) view.findViewById(R.id.cart_caininxihuan_btn);
        ImageView cart_guomanyejingcai_btn = (ImageView) view.findViewById(R.id.cart_guomanyejingcai_btn);
        ImageView cart_remenlianzai_btn = (ImageView) view.findViewById(R.id.cart_remenlianzai_btn);
        cart_huorezhuanti_btn.setOnClickListener(this);
        cart_caininxihuan_btn.setOnClickListener(this);
        cart_guomanyejingcai_btn.setOnClickListener(this);
        cart_remenlianzai_btn.setOnClickListener(this);
        intent = new Intent(activity, CartDetailActivity.class);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HEADCARTS:
                    List<CartRecommend> test = (List<CartRecommend>) msg.obj;
                    if (null != test) {
                        recommends = test;
                        //给界面空间赋值
                        start();
                        tv_head_news_title.setText(recommends.get(0).getData().get(0).getTitle());
                        handler.sendEmptyMessageDelayed(CHANGEHEADNEWSIMAGE, 5000);

                    } else {
                        Toast.makeText(activity, "数据获取失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case YOURLIKE:
                    TellMeWhy tellMeWhy = (TellMeWhy) msg.obj;
                    if (null != tellMeWhy) {
                        operatorOfcainixihuan(tellMeWhy);
                    } else {
                        Toast.makeText(activity, "数据获取失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CHANGEHEADNEWSIMAGE:
                    vp_news_fragment_head.setCurrentItem(vp_news_fragment_head.getCurrentItem() + 1);
                    handler.sendEmptyMessageDelayed(CHANGEHEADNEWSIMAGE, 5000);
                    break;
            }
        }
    };

    private void operatorOfcainixihuan(TellMeWhy tellMeWhy) {
        List<TellMeWhy.DataEntity.DataEntity2> data = tellMeWhy.getData().getData();
        TellMeWhy.DataEntity.DataEntity2 dataEntity1 = data.get(0);
        TellMeWhy.DataEntity.DataEntity2 dataEntity2 = data.get(1);
        TellMeWhy.DataEntity.DataEntity2 dataEntity3 = data.get(2);
        View caininxihuan1 = view.findViewById(R.id.caininxihuan1);
        View caininxihuan2 = view.findViewById(R.id.caininxihuan2);
        View caininxihuan3 = view.findViewById(R.id.caininxihuan3);
        cainixihuanitem1(dataEntity1, caininxihuan1);
        cainixihuanitem1(dataEntity2, caininxihuan2);
        cainixihuanitem1(dataEntity3, caininxihuan3);
    }

    private void cainixihuanitem1(TellMeWhy.DataEntity.DataEntity2 dataEntity, View jinqibikan1) {
        ImageView cart_item_imageview = (ImageView) jinqibikan1.findViewById(R.id.cart_item_imageview);
        TextView cart_item_title = (TextView) jinqibikan1.findViewById(R.id.cart_item_title);
        TextView cart_item_auther = (TextView) jinqibikan1.findViewById(R.id.cart_item_auther);
//        MyApp.getMybitmapUtils().display(cart_item_imageview, dataEntity.getCover());
        new ImageAsyncTask(cart_item_imageview,3).execute(dataEntity.getCover());
        cart_item_title.setText(dataEntity.getTitle());
        cart_item_auther.setText(dataEntity.getAuthors());
        cart_item_imageview.setTag(dataEntity.getId() + "");
        cart_item_imageview.setOnClickListener(this);
    }

    //给界面空间赋值
    private void start() {
        //轮询器赋值
        operatorOfViewPager();
        //今期必看赋值
        operatorOfrecent();
        //火热专题赋值
        operatorOfhotsubject();
//        //猜你喜欢赋值
//        operatorOfyoulike();
        //大师级作者赋值
        operatorOfdashi();
        //国漫也精彩赋值
        operatorOfguomanyejingcai();
        //美漫大事件赋值
        operatorOfmeimandashijian();
        //热门连载赋值
        operatorOfremenlianzai();
        //条漫专区赋值
        operatorOftiaomanzhuanqu();
        //最新上架赋值
        operatorOfzuixinshangjia();


    }

    //最新上架赋值
    private void operatorOfzuixinshangjia() {
        List<CartRecommend.DataEntity> data = recommends.get(9).getData();
        CartRecommend.DataEntity dataEntity1 = data.get(0);
        CartRecommend.DataEntity dataEntity2 = data.get(1);
        CartRecommend.DataEntity dataEntity3 = data.get(2);
        CartRecommend.DataEntity dataEntity4 = data.get(3);
        CartRecommend.DataEntity dataEntity5 = data.get(4);
        CartRecommend.DataEntity dataEntity6 = data.get(5);
        View zuixinshangjia1 = view.findViewById(R.id.zuixinshangjia1);
        View zuixinshangjia2 = view.findViewById(R.id.zuixinshangjia2);
        View zuixinshangjia3 = view.findViewById(R.id.zuixinshangjia3);
        View zuixinshangjia4 = view.findViewById(R.id.zuixinshangjia4);
        View zuixinshangjia5 = view.findViewById(R.id.zuixinshangjia5);
        View zuixinshangjia6 = view.findViewById(R.id.zuixinshangjia6);
        item1(dataEntity1, zuixinshangjia1);
        item1(dataEntity2, zuixinshangjia2);
        item1(dataEntity3, zuixinshangjia3);
        item1(dataEntity4, zuixinshangjia4);
        item1(dataEntity5, zuixinshangjia5);
        item1(dataEntity6, zuixinshangjia6);
    }

    //条漫专区赋值
    private void operatorOftiaomanzhuanqu() {
        List<CartRecommend.DataEntity> data = recommends.get(8).getData();
        CartRecommend.DataEntity dataEntity1 = data.get(0);
        CartRecommend.DataEntity dataEntity2 = data.get(1);
        CartRecommend.DataEntity dataEntity3 = data.get(2);
        CartRecommend.DataEntity dataEntity4 = data.get(3);
        View tiaomanzhuanqu1 = view.findViewById(R.id.tiaomanzhuanqu1);
        View tiaomanzhuanqu2 = view.findViewById(R.id.tiaomanzhuanqu2);
        View tiaomanzhuanqu3 = view.findViewById(R.id.tiaomanzhuanqu3);
        View tiaomanzhuanqu4 = view.findViewById(R.id.tiaomanzhuanqu4);
        item2(dataEntity1, tiaomanzhuanqu1);
        item2(dataEntity2, tiaomanzhuanqu2);
        item2(dataEntity3, tiaomanzhuanqu3);
        item2(dataEntity4, tiaomanzhuanqu4);
    }

    //热门连载赋值
    private void operatorOfremenlianzai() {
        List<CartRecommend.DataEntity> data = recommends.get(7).getData();
        CartRecommend.DataEntity dataEntity1 = data.get(0);
        CartRecommend.DataEntity dataEntity2 = data.get(1);
        CartRecommend.DataEntity dataEntity3 = data.get(2);
        CartRecommend.DataEntity dataEntity4 = data.get(3);
        CartRecommend.DataEntity dataEntity5 = data.get(4);
        CartRecommend.DataEntity dataEntity6 = data.get(5);
        View remenlianzai1 = view.findViewById(R.id.remenlianzai1);
        View remenlianzai2 = view.findViewById(R.id.remenlianzai2);
        View remenlianzai3 = view.findViewById(R.id.remenlianzai3);
        View remenlianzai4 = view.findViewById(R.id.remenlianzai4);
        View remenlianzai5 = view.findViewById(R.id.remenlianzai5);
        View remenlianzai6 = view.findViewById(R.id.remenlianzai6);
        item1(dataEntity1, remenlianzai1);
        item1(dataEntity2, remenlianzai2);
        item1(dataEntity3, remenlianzai3);
        item1(dataEntity4, remenlianzai4);
        item1(dataEntity5, remenlianzai5);
        item1(dataEntity6, remenlianzai6);
    }

    //美漫大事件赋值
    private void operatorOfmeimandashijian() {
        List<CartRecommend.DataEntity> data = recommends.get(6).getData();
        CartRecommend.DataEntity dataEntity1 = data.get(0);
        CartRecommend.DataEntity dataEntity2 = data.get(1);
        CartRecommend.DataEntity dataEntity3 = data.get(2);
        CartRecommend.DataEntity dataEntity4 = data.get(3);
        View meimandashijian1 = view.findViewById(R.id.meimandashijian1);
        View meimandashijian2 = view.findViewById(R.id.meimandashijian2);
        View meimandashijian3 = view.findViewById(R.id.meimandashijian3);
        View meimandashijian4 = view.findViewById(R.id.meimandashijian4);
        item2(dataEntity1, meimandashijian1);
        item2(dataEntity2, meimandashijian2);
        item2(dataEntity3, meimandashijian3);
        item2(dataEntity4, meimandashijian4);
    }

    //国漫也精彩赋值
    private void operatorOfguomanyejingcai() {
        List<CartRecommend.DataEntity> data = recommends.get(5).getData();
        CartRecommend.DataEntity dataEntity1 = data.get(0);
        CartRecommend.DataEntity dataEntity2 = data.get(1);
        CartRecommend.DataEntity dataEntity3 = data.get(2);
        CartRecommend.DataEntity dataEntity4 = data.get(3);
        CartRecommend.DataEntity dataEntity5 = data.get(4);
        CartRecommend.DataEntity dataEntity6 = data.get(5);
        View guomanyejingcai1 = view.findViewById(R.id.guomanyejingcai1);
        View guomanyejingcai2 = view.findViewById(R.id.guomanyejingcai2);
        View guomanyejingcai3 = view.findViewById(R.id.guomanyejingcai3);
        View guomanyejingcai4 = view.findViewById(R.id.guomanyejingcai4);
        View guomanyejingcai5 = view.findViewById(R.id.guomanyejingcai5);
        View guomanyejingcai6 = view.findViewById(R.id.guomanyejingcai6);
        item1(dataEntity1, guomanyejingcai1);
        item1(dataEntity2, guomanyejingcai2);
        item1(dataEntity3, guomanyejingcai3);
        item1(dataEntity4, guomanyejingcai4);
        item1(dataEntity5, guomanyejingcai5);
        item1(dataEntity6, guomanyejingcai6);
    }

    //大师级作者
    private void operatorOfdashi() {
        List<CartRecommend.DataEntity> data = recommends.get(4).getData();
        CartRecommend.DataEntity dataEntity1 = data.get(0);
        CartRecommend.DataEntity dataEntity2 = data.get(1);
        CartRecommend.DataEntity dataEntity3 = data.get(2);
        View dashijia1 = view.findViewById(R.id.dashijia1);
        View dashijia2 = view.findViewById(R.id.dashijia2);
        View dashijia3 = view.findViewById(R.id.dashijia3);
        item3(dataEntity1, dashijia1);
        item3(dataEntity2, dashijia2);
        item3(dataEntity3, dashijia3);
    }

    private void item3(CartRecommend.DataEntity dataEntity1, View dashijia1) {
        ImageView cart_item3_imageview = (ImageView) dashijia1.findViewById(R.id.cart_item3_imageview);
        TextView cart_item3_title = (TextView) dashijia1.findViewById(R.id.cart_item3_title);
//        MyApp.getMybitmapUtils().display(cart_item3_imageview, dataEntity1.getCover());
        new ImageAsyncTask(cart_item3_imageview,3).execute(dataEntity1.getCover());
        cart_item3_title.setText(dataEntity1.getTitle());
        cart_item3_imageview.setTag(dataEntity1.getObj_id() + "");
        cart_item3_imageview.setOnClickListener(this);
    }

    //火热专题赋值
    private void operatorOfhotsubject() {
        List<CartRecommend.DataEntity> data = recommends.get(2).getData();
        CartRecommend.DataEntity dataEntity1 = data.get(0);
        CartRecommend.DataEntity dataEntity2 = data.get(1);
        CartRecommend.DataEntity dataEntity3 = data.get(2);
        CartRecommend.DataEntity dataEntity4 = data.get(3);
        View huorezhuanti1 = view.findViewById(R.id.huorezhuanti1);
        View huorezhuanti2 = view.findViewById(R.id.huorezhuanti2);
        View huorezhuanti3 = view.findViewById(R.id.huorezhuanti3);
        View huorezhuanti4 = view.findViewById(R.id.huorezhuanti4);
        item2(dataEntity1, huorezhuanti1);
        item2(dataEntity2, huorezhuanti2);
        item2(dataEntity3, huorezhuanti3);
        item2(dataEntity4, huorezhuanti4);
    }

    private void item2(CartRecommend.DataEntity dataEntity1, View huorezhuanti1) {
        ImageView cart_item2_imageview = (ImageView) huorezhuanti1.findViewById(R.id.cart_item2_imageview);
        TextView cart_item2_title = (TextView) huorezhuanti1.findViewById(R.id.cart_item2_title);
//        MyApp.getMybitmapUtils().display(cart_item2_imageview, dataEntity1.getCover());
        new ImageAsyncTask(cart_item2_imageview,3).execute(dataEntity1.getCover());
        cart_item2_title.setText(dataEntity1.getTitle());
        cart_item2_imageview.setTag(dataEntity1.getObj_id() + "");
        cart_item2_imageview.setOnClickListener(this);
    }

    //今期必看赋值
    private void operatorOfrecent() {
        List<CartRecommend.DataEntity> data = recommends.get(1).getData();
        CartRecommend.DataEntity dataEntity1 = data.get(0);
        CartRecommend.DataEntity dataEntity2 = data.get(1);
        CartRecommend.DataEntity dataEntity3 = data.get(2);
        View jinqibikan1 = view.findViewById(R.id.jinqibikan1);
        View jinqibikan2 = view.findViewById(R.id.jinqibikan2);
        View jinqibikan3 = view.findViewById(R.id.jinqibikan3);
        item1(dataEntity1, jinqibikan1);
        item1(dataEntity2, jinqibikan2);
        item1(dataEntity3, jinqibikan3);
    }

    private void item1(CartRecommend.DataEntity dataEntity, View jinqibikan1) {
        ImageView cart_item_imageview = (ImageView) jinqibikan1.findViewById(R.id.cart_item_imageview);
        TextView cart_item_title = (TextView) jinqibikan1.findViewById(R.id.cart_item_title);
        TextView cart_item_auther = (TextView) jinqibikan1.findViewById(R.id.cart_item_auther);
//        MyApp.getMybitmapUtils().display(cart_item_imageview, dataEntity.getCover());
        new ImageAsyncTask(cart_item_imageview,3).execute(dataEntity.getCover());
        cart_item_title.setText(dataEntity.getTitle());
        cart_item_auther.setText(dataEntity.getSub_title());
        if (dataEntity.getObj_id() != 0) {
            cart_item_imageview.setTag(dataEntity.getObj_id() + "");
        } else {
            cart_item_imageview.setTag(dataEntity.getId() + "");
        }
        cart_item_imageview.setOnClickListener(this);
    }

    //轮询器赋值
    private void operatorOfViewPager() {
        LinkedList<ImageView> images = new LinkedList<>();
        for (int i = 0; i < recommends.get(0).getData().size(); i++) {
            ImageView imageview = new ImageView(activity);
            imageview.setTag(recommends.get(0).getData().get(i));
//            MyApp.getMybitmapUtils().display(imageview, recommends.get(0).getData().get(i).getCover());
            new ImageAsyncTask(imageview,2).execute(recommends.get(0).getData().get(i).getCover());
            images.add(imageview);
            //给每一个imageview添加点击事件
            imageview.setId(viewpagers[i]);
            imageview.setOnClickListener(this);
        }
        CartRecommendViewPagerAdapter adapter = new CartRecommendViewPagerAdapter(images);
        vp_news_fragment_head.setAdapter(adapter);///
//        vp_news_fragment_head.setCurrentItem(1000);
        rg_news_fragment_head.check(R.id.rb_head_one);
    }

    private final int[] viewpagers = new int[]{1001, 1002, 1003, 1004, 1005};


    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        String tag=null;
        if (v.getTag() instanceof String){
            tag = (String) v.getTag();
        }
        CartRecommend.DataEntity dataEntity=null;
        if (v.getTag() instanceof CartRecommend.DataEntity){
            dataEntity = (CartRecommend.DataEntity) v.getTag();
        }
        switch (v.getId()) {
            case 1001://viewpager点击事件
            case 1002:
            case 1003:
            case 1004:
            case 1005:
                if (dataEntity.getId()!=0){
                    intent = new Intent(activity, CartDetailActivity.class);
                    intent.putExtra("id", dataEntity.getId() + "");
                    startActivity(intent);
                }else if (dataEntity.getUrl().equals("")){
                    intent = new Intent(activity, CartWebActivity.class);
                    intent.putExtra("webUrl", dataEntity.getUrl() + "");
                    startActivity(intent);
                }
                Toast.makeText(activity, "轮续期+web：" + tag, Toast.LENGTH_LONG).show();
                break;
            case R.id.cart_item_imageview:
                switch (((View) (v.getParent().getParent().getParent().getParent())).getId()) {
                    case R.id.fragment_cart_jinqibikan://近期必看点击事件
//                        Toast.makeText(activity, "近期必看+id：" + tag, Toast.LENGTH_LONG).show();
                        intent = new Intent(activity, CartDetailActivity.class);
                        intent.putExtra("id", tag + "");
                        startActivity(intent);
                        break;
                    case R.id.fragment_cart_guomanyejingcai://国漫也精彩点击事件
//                        Toast.makeText(activity, "国漫也精彩+id：" + tag, Toast.LENGTH_LONG).show();
                         intent = new Intent(activity, CartDetailActivity.class);
                        intent.putExtra("id", tag + "");
                        startActivity(intent);
                        break;
                    case R.id.fragment_cart_remenlianzai://热门连载点击事件
//                        Toast.makeText(activity, "热门连载+id：" + tag, Toast.LENGTH_LONG).show();
                        intent = new Intent(activity, CartDetailActivity.class);
                        intent.putExtra("id", tag + "");
                        startActivity(intent);
                        break;
                    case R.id.fragment_cart_zuixinshangjia://最新上架点击事件
//                        Toast.makeText(activity, "最新上架+id：" + tag, Toast.LENGTH_LONG).show();
                        intent = new Intent(activity, CartDetailActivity.class);
                        intent.putExtra("id", tag + "");
                        startActivity(intent);
                        break;
                    case R.id.fragment_cart_caininxihuan://猜你喜欢点击事件
//                        Toast.makeText(activity, "猜你喜欢+id：" + tag, Toast.LENGTH_LONG).show();
                        intent = new Intent(activity, CartDetailActivity.class);
                        intent.putExtra("id", tag + "");
                        startActivity(intent);
                        break;
                }
                break;
            case R.id.cart_item2_imageview:
                switch (((View) (v.getParent().getParent().getParent().getParent())).getId()) {
                    case R.id.fragment_cart_huorezhuanti://热门主题点击事件
                        Toast.makeText(activity, "热门主题+id：" + tag, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.fragment_cart_meimandashijian://美漫大事件点击事件
                        Toast.makeText(activity, "美漫大事件+id：" + tag, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.fragment_cart_tiaomanzhuanqu://条漫专区点击事件
                        Toast.makeText(activity, "条漫专区+id：" + tag, Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case R.id.cart_item3_imageview:
                switch (((View) (v.getParent().getParent().getParent().getParent())).getId()) {
                    case R.id.fragment_cart_dashijia://大师级作者点击事件
                        Toast.makeText(activity, "大师级作者+id：" + tag, Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case R.id.cart_huorezhuanti_btn://火热专题刷新
                Toast.makeText(activity, "火热专题刷新", Toast.LENGTH_LONG).show();
                break;
            case R.id.cart_caininxihuan_btn://猜你喜欢刷新
                Toast.makeText(activity, "猜你喜欢刷新", Toast.LENGTH_LONG).show();
                break;
            case R.id.cart_guomanyejingcai_btn://国漫也精彩刷新
                Toast.makeText(activity, "国漫也精彩刷新", Toast.LENGTH_LONG).show();
                break;
            case R.id.cart_remenlianzai_btn://热门连载刷新
                Toast.makeText(activity, "热门连载刷新", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
