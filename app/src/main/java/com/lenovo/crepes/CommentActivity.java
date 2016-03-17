package com.lenovo.crepes;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lenovo.crepes.adapters.CommentAdapter;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.Comment;
import com.lenovo.crepes.entities.CommentAmount;
import com.lenovo.crepes.entities.SendCommentMeg;
import com.lenovo.crepes.interf.AddComment;
import com.lenovo.crepes.utils.MyHttpUtils;
import com.lidroid.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener, AddComment {
    private final int COMMENTAMOUNT = 100;
    private final int COMMENT = 200;
    private final int SENDCOMMENT = 300;
    //    private final String newComment = "http://v2.api.dmzj.com/old/comment/2/0/2847/0.json";
    private int v = 2;
    private int conmentType = 0;
    private int newsId;
    private int page = 0;
    private boolean isAddComment;

    private TextView tv_comment_amount;

    private List<Comment> commentList;
    private List<Comment> formatCommentList;
    private CommentAdapter commentAdapter;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllist_comment;
    private EditText et_comment;
    private Button btn_send_comment;
    private Comment addComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        newsId = getIntent().getIntExtra("newsId", -1);
        dialog = new ProgressDialog(this);
        if (newsId != -1) {
            dialog.show();
            MyHttpUtils.sendDataOfGet("http://v2.api.dmzj.com/old/comment/total/2/" + newsId + ".json", handler, new CommentAmount(), COMMENTAMOUNT);
        }

        initView();
    }

    private void initView() {

        findViewById(R.id.iv_comment_back).setOnClickListener(this);

        RadioGroup rg_comment_type = (RadioGroup) findViewById(R.id.rg_comment_type);
        tv_comment_amount = (TextView) findViewById(R.id.tv_comment_amount);

        rg_comment_type.check(R.id.rb_comment_new);

        et_comment = (EditText) findViewById(R.id.et_comment);
        btn_send_comment = (Button) findViewById(R.id.btn_send_comment);
        btn_send_comment.setOnClickListener(this);

        rg_comment_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_comment_new:
                        conmentType = 0;
                        page = 0;
                        commentList.clear();
                        MyHttpUtils.sendDataArray(Common.commentUrl + v + "/" + conmentType + "/" + newsId + "/" + page + ".json", handler, new Comment(), COMMENT);
                        break;
                    case R.id.rb_comment_hot:
                        conmentType = 1;
                        page = 0;
                        commentList.clear();
                        MyHttpUtils.sendDataArray(Common.commentUrl + v + "/" + conmentType + "/" + newsId + "/" + page + ".json", handler, new Comment(), COMMENT);
                        break;
                }
            }
        });


        pulllist_comment = (PullToRefreshListView) findViewById(R.id.pulllist_comment);
        pulllist_comment.setMode(PullToRefreshBase.Mode.BOTH);
        commentList = new ArrayList<>();
        formatCommentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(this, commentList, R.layout.item_comment);
        pulllist_comment.setAdapter(commentAdapter);

        MyHttpUtils.sendDataArray(Common.commentUrl + v + "/" + conmentType + "/" + newsId + "/" + page + ".json", handler, new Comment(), COMMENT);

        pulllist_comment.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                commentList.clear();
                page = 0;
                MyHttpUtils.sendDataArray(Common.commentUrl + v + "/" + conmentType + "/" + newsId + "/" + page + ".json", handler, new Comment(), COMMENT);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                MyHttpUtils.sendDataArray(Common.commentUrl + v + "/" + conmentType + "/" + newsId + "/" + page + ".json", handler, new Comment(), COMMENT);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COMMENTAMOUNT:
                    CommentAmount commentAmount = ((CommentAmount) msg.obj);
                    if (commentAmount != null) {
                        if (commentAmount.getCode() == 0 && "ok".equals(commentAmount.getMsg())) {
                            tv_comment_amount.setText(commentAmount.getAmount() + "条");
                        } else {
                            Toast.makeText(CommentActivity.this, commentAmount.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case COMMENT:
                    formatCommentList.clear();
                    formatCommentList.addAll((List<Comment>) msg.obj);
                    if (formatCommentList.size() > 0) {
                        formatComment();
                        commentAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(CommentActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                    pulllist_comment.onRefreshComplete();
                    dialog.dismiss();
                    break;
                case SENDCOMMENT://发送评论返回结果
                    if (msg.obj instanceof SendCommentMeg) {
                        SendCommentMeg meg = (SendCommentMeg) msg.obj;
                        if (meg != null && meg.getResult() == 1) {
                            MyHttpUtils.sendDataArray(Common.commentUrl + v + "/" + conmentType + "/" + newsId + "/" + page + ".json", handler, new Comment(), COMMENT);
                            et_comment.setText("");
                        }
                        Toast.makeText(CommentActivity.this, "" + meg.getMsg(), Toast.LENGTH_LONG).show();

                    }
                    break;
            }
        }
    };

    private void formatComment() {
        for (int i = 0; i < formatCommentList.size(); i++) {
            Comment comment = formatCommentList.get(i);
            if (comment.getReply().getTotal() == 0) {
                commentList.add(comment);
            } else {
                List<Comment.ReplyEntity.DataEntity> data = comment.getReply().getData();
                for (int j = 0; j < data.size(); j++) {
                    Comment.ReplyEntity.DataEntity dataEntity = data.get(j);
                    Comment c = new Comment();
                    c.setHot_comment_amount(-1);
                    c.setAuthor(dataEntity.getAuthor());
                    c.setAuthor_id(dataEntity.getAuthor_id());
                    c.setAvatar_url(dataEntity.getAvatar_url());
                    c.setContent(dataEntity.getContent());
                    c.setCreatetime(dataEntity.getCreatetime());
                    c.setId(dataEntity.getId());
                    c.setIsused(dataEntity.getIsused());
                    c.setNickname(dataEntity.getNickname());
                    c.setObj_id(dataEntity.getObj_id());
                    c.setPid(dataEntity.getPid());
                    c.setUid(dataEntity.getUid());
                    c.setUp(dataEntity.getUp());
                    commentList.add(c);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_comment_back://返回
                finish();
                break;
            case R.id.btn_send_comment://发送评论
                if (MyApp.getUserData() != null) {

                    if (et_comment != null) {
                        String s = et_comment.getText().toString();
                        if (s != null && !s.isEmpty()) {
                            String author_id = "";
                            String author = "";
                            int obj_id = newsId;
                            if (isAddComment) {
                                author_id = "" + addComment.getAuthor_id();
                                obj_id = addComment.getObj_id();
                                author = addComment.getNickname();
                            }
                            sendOrAddComment(author_id, obj_id, MyApp.getUserData().getPhoto(), MyApp.getUserData().getUid(), s, author, MyApp.getUserData().getNickname());
                        }
                    }
                }else {
                    Toast.makeText(this,"请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void addCommentTo(Comment comment) {
        et_comment.setFocusable(true);
        et_comment.setFocusableInTouchMode(true);
        et_comment.requestFocus();
        et_comment.setHint("回复：" + comment.getNickname());
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        isAddComment = true;
        addComment = comment;
    }

    public void sendOrAddComment(String author_id, int obj_id, String avatar_url, String uid, String content, String author, String nickname) {
        RequestParams requestParams = new RequestParams();
        requestParams.addBodyParameter("author_id", "" + author_id);
        requestParams.addBodyParameter("obj_id", "" + obj_id);
        requestParams.addBodyParameter("avatar_url", "" + avatar_url);
        requestParams.addBodyParameter("uid", "" + uid);
        requestParams.addBodyParameter("content", "" + content);
        requestParams.addBodyParameter("author", author);
        requestParams.addBodyParameter("nickname", nickname);
        requestParams.addBodyParameter("type", "" + 2);
        requestParams.addBodyParameter("pid", "" + 0);
        MyHttpUtils.sendDataOfPost(Common.sendCommentUrl, requestParams, handler, new SendCommentMeg(), SENDCOMMENT);
    }
}
