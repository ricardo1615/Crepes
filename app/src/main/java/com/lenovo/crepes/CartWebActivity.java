package com.lenovo.crepes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CartWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_web);
        Intent intent = getIntent();
        String webUrl = intent.getStringExtra("webUrl");
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
    }
}
