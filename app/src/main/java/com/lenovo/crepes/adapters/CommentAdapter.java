package com.lenovo.crepes.adapters;

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
import com.lenovo.crepes.entities.Comment;
import com.lenovo.crepes.utils.DataTransUtils;
import com.lenovo.crepes.utils.GlideRoundTransform;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/18.
 */
public class CommentAdapter extends CustomerAdapter<Comment> implements View.OnClickListener {
    private List<Comment> list;
    private Context context;

    public CommentAdapter(Context context, List list, int resId) {
        super(context, list, resId);
        this.list = list;
        this.context = context;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        View view = viewHolder.getView();

        ImageView iv_comment_author_photo = (ImageView) view.findViewById(R.id.iv_comment_author_photo);
        TextView tv_comment_author_name = (TextView) view.findViewById(R.id.tv_comment_author_name);

        if (list.get(position).getHot_comment_amount() != -1) {
//            Glide.with(context).load(list.get(position).getAvatar_url()).transform(new GlideRoundTransform(context, 40)).into(iv_comment_author_photo);
            String avatar_url = list.get(position).getAvatar_url();
            iv_comment_author_photo.setTag(avatar_url);
            new ImageAsyncTask(iv_comment_author_photo,1).execute(avatar_url);
        }
        tv_comment_author_name.setText(list.get(position).getNickname());
        ((TextView) view.findViewById(R.id.tv_comment_content)).setText(list.get(position).getContent());
        ((TextView) view.findViewById(R.id.tv_comment_date)).setText(DataTransUtils.transdata(list.get(position).getCreatetime() * 1000L, Common.dateFormat));
        ((TextView) view.findViewById(R.id.tv_comment_praise)).setText("" + list.get(position).getUp());

        view.findViewById(R.id.iv_comment_praise).setOnClickListener(this);
        view.findViewById(R.id.tv_comment_comment).setOnClickListener(this);
        iv_comment_author_photo.setOnClickListener(this);
        tv_comment_author_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_comment_author_name://其它用户详情
            case R.id.iv_comment_author_photo:
                break;
            case R.id.iv_comment_praise://点赞
                break;
            case R.id.tv_comment_comment://评论
                break;
        }
    }
}
