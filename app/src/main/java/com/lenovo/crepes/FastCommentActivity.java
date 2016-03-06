package com.lenovo.crepes;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.lenovo.crepes.customerView.FlowLayout;
import com.lenovo.crepes.entities.FastNewsComment;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class FastCommentActivity extends AppCompatActivity {

    private FlowLayout viewById;
    private final String tucaoUrlHead = "http://v2.api.dmzj.com/viewPoint/3/100/";
    private final String tucaoUrlEnd = ".json";
    private final int GETTUCAO = 100;
    private int newsId;
    private List<FastNewsComment> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_comment);

        initView();
    }

    private void initView() {
        commentList = new ArrayList<>();
        newsId = getIntent().getIntExtra("newsId", -1);
        viewById = (FlowLayout) findViewById(R.id.fll_tucao);
        MyHttpUtils.sendDataArray(tucaoUrlHead + newsId + tucaoUrlEnd, handler, new FastNewsComment(), GETTUCAO);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GETTUCAO:
                    if (commentList != null) {
                        commentList.addAll(((List<FastNewsComment>) msg.obj));
                        for (int i = 0; i < commentList.size(); i++) {
                            TextView textView = new TextView(FastCommentActivity.this);
                            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,10,10,10);
                            textView.setText(commentList.get(i).getContent());
                            textView.setTextAppearance(FastCommentActivity.this,R.style.text_flag_01);
                            textView.setLayoutParams(params);
                            textView.setBackgroundResource(R.drawable.flag_01);
                            viewById.addView(textView);
                        }
                    }
                    break;
            }
        }
    };
}
