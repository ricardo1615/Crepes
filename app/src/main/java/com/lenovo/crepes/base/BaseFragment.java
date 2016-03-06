package com.lenovo.crepes.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Lenovo on 2015/11/10.
 */
public class BaseFragment extends Fragment {
    public Activity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (Activity) context;
    }
}
