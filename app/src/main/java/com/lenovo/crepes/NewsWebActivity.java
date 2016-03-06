package com.lenovo.crepes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.lenovo.crepes.entities.ResultTwo;
import com.lenovo.crepes.utils.MyHttpUtils;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;

public class NewsWebActivity extends AppCompatActivity implements View.OnClickListener {

    private int newsId;
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);
        Intent intent = getIntent();
        String webUrl = intent.getStringExtra("webUrl");
        newsId = intent.getIntExtra("newsId", -1);

        WebView wv_news = (WebView) findViewById(R.id.wv_news);
        wv_news.getSettings().setJavaScriptEnabled(true);
        wv_news.setWebViewClient(new WebViewClient());
        wv_news.getSettings().setSupportZoom(true);
        wv_news.getSettings().supportMultipleWindows();
        wv_news.getSettings().setLoadsImagesAutomatically(true);
        wv_news.setWebChromeClient(new WebChromeClient());

        if (webUrl != null) {
            wv_news.loadUrl(webUrl);
        }

        //返回按钮
        findViewById(R.id.iv_news_web_back).setOnClickListener(this);
        //底端按钮
        findViewById(R.id.iv_web_praise).setOnClickListener(this);
        findViewById(R.id.tv_web_praise).setOnClickListener(this);
        findViewById(R.id.iv_web_share).setOnClickListener(this);
        findViewById(R.id.tv_web_share).setOnClickListener(this);
        findViewById(R.id.iv_web_comment).setOnClickListener(this);
        findViewById(R.id.tv_web_comment).setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_news_web_back:
                finish();
                break;
            case R.id.iv_web_praise:
            case R.id.tv_web_praise:
                MyHttpUtils.sendDataOfGet("http://v2.api.dmzj.com/article/mood/" + newsId, handler, new ResultTwo(), 100);
                break;
            case R.id.iv_web_share:
            case R.id.tv_web_share:
                mController.openShare(this, false);
                break;
            case R.id.iv_web_comment:
            case R.id.tv_web_comment:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra("newsId", newsId);
                startActivity(intent);
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                ResultTwo resultTwo = (ResultTwo) msg.obj;
                if (resultTwo.getCode() == 0) {
                    Toast.makeText(NewsWebActivity.this, "点赞成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewsWebActivity.this, resultTwo.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
