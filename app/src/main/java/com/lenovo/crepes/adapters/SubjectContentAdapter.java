package com.lenovo.crepes.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.crepes.LoginAndRegistActivity;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.Comment;
import com.lenovo.crepes.entities.LoginResult;
import com.lenovo.crepes.entities.ResultTwo;
import com.lenovo.crepes.entities.SubjectDetail;
import com.lenovo.crepes.utils.MyHttpUtils;
import com.lidroid.xutils.http.RequestParams;

import java.util.List;

/**
 * Created by Administrator on 2016/3/28.
 */
public class SubjectContentAdapter extends CustomerAdapter<SubjectDetail.ComicsEntity> implements View.OnClickListener {
    private Context context;
    private Handler handler;

    public SubjectContentAdapter(Context context, List<SubjectDetail.ComicsEntity> list, int resId, Handler handler) {
        super(context, list, resId);
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        SubjectDetail.ComicsEntity comicsEntity = list.get(position);
        ImageView ivComicCover = (ImageView) viewHolder.findView(R.id.iv_comic_cover);
        ivComicCover.setTag(comicsEntity.getCover());
        new ImageAsyncTask(ivComicCover, 4).execute(comicsEntity.getCover());

        ((TextView) viewHolder.findView(R.id.iv_comic_name)).setText(comicsEntity.getName());
        ((TextView) viewHolder.findView(R.id.iv_comic_brief)).setText(comicsEntity.getRecommend_brief());
        ((TextView) viewHolder.findView(R.id.iv_comic_reason)).setText(comicsEntity.getRecommend_reason());

        viewHolder.findView(R.id.tv_subject_subscribe).setTag(position);
        viewHolder.findView(R.id.tv_subject_subscribe).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_subject_subscribe) {
            //订阅
            LoginResult.DataEntity userData = MyApp.getUserData();
            if (userData != null && userData.getUid() != null) {
                int position = (int) v.getTag();
                RequestParams params = new RequestParams();
                params.addBodyParameter("obj_ids", "" + list.get(position).getId());
                params.addBodyParameter("uid", userData.getUid());
                params.addBodyParameter("type", "mh");
                MyHttpUtils.sendDataOfPost(Common.subscribeUrl, params, handler, new ResultTwo(), 200);
            }else {
                Toast.makeText(context,"请先登录",Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, LoginAndRegistActivity.class));
            }
        }
    }
}
