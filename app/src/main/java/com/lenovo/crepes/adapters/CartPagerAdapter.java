package com.lenovo.crepes.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.lenovo.crepes.base.BaseFragment;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/16.
 */
public class CartPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list;
    private FragmentManager fm;

    public CartPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.fm=fm;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return  list.size();
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
