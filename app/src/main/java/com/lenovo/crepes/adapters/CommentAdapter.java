package com.lenovo.crepes.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.crepes.R;
import com.lenovo.crepes.adapters.base.CustomerAdapter;
import com.lenovo.crepes.adapters.base.ViewHolder;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.Comment;
import com.lenovo.crepes.entities.ResultTwo;
import com.lenovo.crepes.interf.AddComment;
import com.lenovo.crepes.utils.DataTransUtils;
import com.lenovo.crepes.utils.MyHttpUtils;

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
            new ImageAsyncTask(iv_comment_author_photo, 1).execute(avatar_url);
        }
        tv_comment_author_name.setText(list.get(position).getNickname());
        ((TextView) view.findViewById(R.id.tv_comment_content)).setText(list.get(position).getContent());
        ((TextView) view.findViewById(R.id.tv_comment_date)).setText(DataTransUtils.transdata(list.get(position).getCreatetime() * 1000L, Common.dateFormat));
        ((TextView) view.findViewById(R.id.tv_comment_praise)).setText("" + list.get(position).getUp());

        view.findViewById(R.id.iv_comment_praise).setOnClickListener(this);
        view.findViewById(R.id.iv_comment_praise).setTag(position);
        view.findViewById(R.id.tv_comment_comment).setOnClickListener(this);
        view.findViewById(R.id.tv_comment_comment).setTag(position);
        iv_comment_author_photo.setOnClickListener(this);
        tv_comment_author_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = -1;
        switch (v.getId()) {
            case R.id.tv_comment_author_name://其它用户详情
            case R.id.iv_comment_author_photo:
                break;
            case R.id.iv_comment_praise://点赞
                id = (int) v.getTag();
                if (id != -1) {
                    MyHttpUtils.sendDataOfGet(formatCommentZanUrl(list.get(id).getId(), list.get(id).getObj_id()), handler, new ResultTwo(), 100);
                }
                break;
            case R.id.tv_comment_comment://评论
                id = (int) v.getTag();
                if (id != -1) {
                    ((AddComment) context).addCommentTo(list.get(id));
                }
                break;
        }
    }

    private String formatCommentZanUrl(int comment_id, int obj_id) {
//        comment_id=352716&obj_id=2879&type=2
        return Common.commentZanUrl + "comment_id=" + comment_id + "&obj_id=" + obj_id + "&type=2";
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100://点赞
                    if (msg != null) {
                        ResultTwo resultTwo = (ResultTwo) msg.obj;
                        Toast.makeText(context, "" +resultTwo.getMsg(),Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
