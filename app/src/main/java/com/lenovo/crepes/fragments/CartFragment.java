package com.lenovo.crepes.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.CartPagerAdapter;
import com.lenovo.crepes.base.BaseFragment;
import com.lenovo.crepes.fragments.cartfragments.CategoryFragment;
import com.lenovo.crepes.fragments.cartfragments.RankFragment;
import com.lenovo.crepes.fragments.cartfragments.RecommendFragment;
import com.lenovo.crepes.fragments.cartfragments.SubjectFragment;
import com.lenovo.crepes.fragments.cartfragments.UpdateFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener,
        ViewPager.OnPageChangeListener {
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private ViewPager cart_viewpager;
    private List<BaseFragment> list;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        initVIew(view);
        return view;
    }

    private void initVIew(View view) {

        fragmentManager = getChildFragmentManager();
        radioGroup = (RadioGroup) view.findViewById(R.id.cart_radio_group);
        ImageView cart_search = (ImageView) view.findViewById(R.id.cart_search);
        cart_search.setOnClickListener(this);
        cart_viewpager = (ViewPager) view.findViewById(R.id.cart_viewpager);
        radioGroup.setOnCheckedChangeListener(this);
        cart_viewpager.addOnPageChangeListener(this);
        list = new LinkedList<>();
        CategoryFragment categoryFragment = new CategoryFragment();
        RankFragment rankFragment = new RankFragment();
        RecommendFragment recommendFragment = new RecommendFragment();
        SubjectFragment subjectFragment = new SubjectFragment();
        UpdateFragment updateFragment = new UpdateFragment();
        list.add(recommendFragment);
        list.add(updateFragment);
        list.add(categoryFragment);
        list.add(rankFragment);
        list.add(subjectFragment);
        CartPagerAdapter adapter = new CartPagerAdapter(fragmentManager, list);
        cart_viewpager.setAdapter(adapter);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //当点击标题后viewPager相应的改变
        cart_viewpager.setCurrentItem(tran2(checkedId));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_search://搜索事件
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //当viewpager改变的时候，使radiogroup改变
        radioGroup.check(tran(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private int tran(int position) {
        switch (position) {
            case 0:
                return R.id.cart_recommond;
            case 1:
                return R.id.cart_update;
            case 2:
                return R.id.cart_category;
            case 3:
                return R.id.cart_rank;
            case 4:
                return R.id.cart_subject;
        }
        return -1;
    }

    private int tran2(int resId) {
        switch (resId) {
            case R.id.cart_recommond:
                return 0;
            case R.id.cart_update:
                return 1;
            case R.id.cart_category:
                return 2;
            case R.id.cart_rank:
                return 3;
            case R.id.cart_subject:
                return 4;
        }
        return -1;
    }



}
