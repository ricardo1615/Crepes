package com.lenovo.crepes.adapters.news;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.NormalNews;
import com.lenovo.crepes.utils.DataTransUtils;
import com.lenovo.crepes.utils.GlideRoundTransform;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/17.
 */
public class NormalNewsAdapter extends CustomerAdapter<NormalNews> implements View.OnClickListener {
    private List<NormalNews> list;
    private Context context;

    public NormalNewsAdapter(Context context, List<NormalNews> list, int resId) {
        super(context, list, resId);
        this.list = list;
        this.context = context;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        View view = viewHolder.getView();

        ImageView iv_news_author_photo = (ImageView) view.findViewById(R.id.iv_news_author_photo);
        TextView tv_news_author_name = (TextView) view.findViewById(R.id.tv_news_author_name);

//        Glide.with(context).load(list.get(position).getRow_pic_url()).centerCrop().into(((ImageView) view.findViewById(R.id.iv_normalnews_photo)));
//        Glide.with(context).load(list.get(position).getCover()).transform(new GlideRoundTransform(context, 10)).into(iv_news_author_photo);
        //头像
        String coverUrl = list.get(position).getCover();
        iv_news_author_photo.setTag(coverUrl);
        new ImageAsyncTask(iv_news_author_photo,1).execute(coverUrl);

        //图片
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_normalnews_photo);
        String imgUrl = list.get(position).getRow_pic_url();
        imageView.setTag(imgUrl);
        new ImageAsyncTask(imageView,0).execute(imgUrl);

        ((TextView) view.findViewById(R.id.tv_normalnews_title)).setText((list.get(position).getTitle()));

        ((TextView) view.findViewById(R.id.tv_normalnews_date)).setText(DataTransUtils.transdata((list.get(position).getCreate_time()) * 1000L, Common.dateFormat));

        tv_news_author_name.setText(list.get(position).getNickname());

        //点击到作者详情页面
        iv_news_author_photo.setOnClickListener(this);
        tv_news_author_name.setOnClickListener(this);
    }

    /**
     * 跳转到详情
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }
}
