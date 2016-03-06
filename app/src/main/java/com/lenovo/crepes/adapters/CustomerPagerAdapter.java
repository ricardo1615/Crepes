package com.lenovo.crepes.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/10.
 */
public class CustomerPagerAdapter<R> extends PagerAdapter {
    private List<View> data;

    public CustomerPagerAdapter(List<View> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageView = data.get(position );
        container.addView(imageView);
        return imageView;
    }


}
