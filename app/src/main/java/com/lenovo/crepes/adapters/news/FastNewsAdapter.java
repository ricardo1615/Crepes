package com.lenovo.crepes.adapters.news;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.crepes.FastCommentActivity;
import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.FastNews;
import com.lenovo.crepes.utils.DataTransUtils;
import com.lenovo.crepes.utils.GlideRoundTransform;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/17.
 */
public class FastNewsAdapter extends CustomerAdapter<FastNews> implements View.OnClickListener {
    private List<FastNews> list;
    private Context context;

    public FastNewsAdapter(Context context, List<FastNews> list, int resId) {
        super(context, list, resId);
        this.list = list;
        this.context = context;
    }

    @Override
    public void fillData(ViewHolder viewHolder, int position) {
        View view = viewHolder.getView();
        //头像
        Glide.with(context).load(list.get(position).getCover()).transform(new GlideRoundTransform(context, 10)).into((ImageView) view.findViewById(R.id.iv_fast_author_photo));
        //图片
        Glide.with(context).load(list.get(position).getImg()).into((ImageView) view.findViewById(R.id.iv_fast_photo));

        Button btn_fast_discuss = (Button) view.findViewById(R.id.btn_fast_discuss);
        Button btn_fast_share = (Button) view.findViewById(R.id.btn_fast_share);
        btn_fast_discuss.setTag(position);

        //作者名
        ((TextView) view.findViewById(R.id.tv_fast_author_name)).setText(list.get(position).getNickname());
        //内容
        ((TextView) view.findViewById(R.id.tv_fast_title)).setText(list.get(position).getContent());
        //评论数
        btn_fast_discuss.setText("评论（" + list.get(position).getVote_amount() + "）");
        //时间
        ((TextView) view.findViewById(R.id.tv_fast_date)).setText(DataTransUtils.transdata(list.get(position).getUpdatetime() * 1000L, Common.dateFormat));

        btn_fast_discuss.setOnClickListener(this);
        btn_fast_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fast_share://分享
                break;
            case R.id.btn_fast_discuss://评论
                Intent intent = new Intent(context, FastCommentActivity.class);
                int p = (int) v.getTag();
                intent.putExtra("newsId",list.get(p).getId());
                context.startActivity(intent);
                break;
        }
    }
}
