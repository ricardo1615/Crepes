package com.lenovo.crepes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.crepes.utils.FileUtil;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_file_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();
    }

    private void initView() {
        findViewById(R.id.net_setting).setOnClickListener(this);
        findViewById(R.id.comic_setting).setOnClickListener(this);
        findViewById(R.id.novel_setting).setOnClickListener(this);
        findViewById(R.id.upadte_setting).setOnClickListener(this);
        findViewById(R.id.download_where).setOnClickListener(this);
        findViewById(R.id.clear_cache).setOnClickListener(this);
        findViewById(R.id.suggestion_setting).setOnClickListener(this);
        findViewById(R.id.about_setting).setOnClickListener(this);

        fillData();
    }

    private void fillData() {
        tv_file_size = (TextView) findViewById(R.id.tv_file_size);
        tv_file_size.setText(FileUtil.getFinalFileSize(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.net_setting://移动网路设置
                break;
            case R.id.comic_setting://漫画阅读设置
                break;
            case R.id.novel_setting://小说阅读设置
                break;
            case R.id.upadte_setting://更新提醒设置
                break;
            case R.id.download_where://下载保存位置
                break;
            case R.id.clear_cache://清空缓存
                FileUtil.deleteAllCacheFile(this);
                Toast.makeText(this, "xiu~xiu~xiu", Toast.LENGTH_SHORT).show();
                tv_file_size.setText("0M");
                break;
            case R.id.suggestion_setting://意见反馈
                break;
            case R.id.about_setting://关于我们
                break;
            default:
                break;
        }
    }
}
