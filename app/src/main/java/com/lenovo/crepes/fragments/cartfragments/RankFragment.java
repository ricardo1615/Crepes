package com.lenovo.crepes.fragments.cartfragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.crepes.R;
import com.lenovo.crepes.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends BaseFragment {


    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }


}
