package com.lenovo.crepes.entities;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.lenovo.crepes.R;


/**
 * Created by Administrator on 2016/3/15.
 */
public class LoadingDialog extends DialogFragment {
    Context context;
    private TextView dialogMessage;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogMessage = (TextView) view.findViewById(R.id.dialog_message);
        return view;
    }

}
