package com.lenovo.crepes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lenovo.crepes.adapters.Novels.NovelchapterAdapter;
import com.lenovo.crepes.base.GridViewForScrollView;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.NovelDetail;
import com.lenovo.crepes.utils.DataTransUtils;
import com.lenovo.crepes.utils.MyHttpUtils;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;

import java.util.Collections;
import java.util.List;

public class NovelDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private String url = "http://v2.api.dmzj.com/novel/";
    //    http://v2.api.dmzj.com/novel/download/1291_4693_30396.txt
//    http://v2.api.dmzj.com/novel/download/1291_4693_30397.txt
    private final int NOVELDETAIL = 100;
    private TextView novel_detail_title;
    private ImageView novel_detail_img;
    private TextView novel_detail_name1;
    private TextView novel_detail_type1;
    private TextView novel_detail_renqi;
    private TextView novel_detail_dingyueNum;
    private TextView novel_detail_time;
    private TextView novel_detail_intro;
    private TextView novel_detail_jindu1;
    private GridViewForScrollView novel_detail_cap;
    private NovelDetail novelDetail;
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_detail);

        int id = getIntent().getIntExtra("id", -1);

        if (id != -1) {
            MyHttpUtils.sendDataOfGet(url + id + ".json", handler, new NovelDetail(), NOVELDETAIL);
        }

        findViewById(R.id.novel_detail_back).setOnClickListener(this);
        novel_detail_title = (TextView) findViewById(R.id.novel_detail_title);
        novel_detail_name1 = (TextView) findViewById(R.id.novel_detail_name1);
        novel_detail_type1 = (TextView) findViewById(R.id.novel_detail_type1);
        novel_detail_renqi = (TextView) findViewById(R.id.novel_detail_renqi);
        novel_detail_dingyueNum = (TextView) findViewById(R.id.novel_detail_dingyueNum);
        novel_detail_time = (TextView) findViewById(R.id.novel_detail_time);
        novel_detail_intro = (TextView) findViewById(R.id.novel_detail_intro);
        novel_detail_jindu1 = (TextView) findViewById(R.id.novel_detail_jindu1);

        novel_detail_cap = (GridViewForScrollView) findViewById(R.id.novel_detail_cap);

        novel_detail_img = (ImageView) findViewById(R.id.novel_detail_img);

        findViewById(R.id.novel_detail_expand).setOnClickListener(this);
        findViewById(R.id.novel_detail_read).setOnClickListener(this);
        findViewById(R.id.novel_more_comment).setOnClickListener(this);
        findViewById(R.id.tv_novel_share).setOnClickListener(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NOVELDETAIL:
                    novelDetail = (NovelDetail) msg.obj;
                    Collections.reverse(novelDetail.getVolume());
                    NovelchapterAdapter adapter = new NovelchapterAdapter(NovelDetailActivity.this, novelDetail.getVolume(), R.layout.item_novel_juan);
                    novel_detail_cap.setAdapter(adapter);

                    novel_detail_cap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(NovelDetailActivity.this, NovelChapterActivity.class);
                            intent.putExtra("id", novelDetail.getId());
                            intent.putExtra("vid", novelDetail.getVolume().get(position).getId());
                            startActivity(intent);

                        }
                    });

                    novel_detail_title.setText(novelDetail.getName());
                    Glide.with(NovelDetailActivity.this).load(novelDetail.getCover()).centerCrop().into(novel_detail_img);
                    novel_detail_name1.setText(novelDetail.getAuthors());
                    List<String> types = novelDetail.getTypes();
                    StringBuffer buffer = new StringBuffer();
                    for (int i = 0; i < types.size(); i++) {
                        buffer.append(types.get(i));
                    }
                    novel_detail_type1.setText(buffer.toString());
                    novel_detail_renqi.setText("" + novelDetail.getHot_hits());
                    novel_detail_dingyueNum.setText("" + novelDetail.getSubscribe_num());
                    novel_detail_time.setText(DataTransUtils.transdata(novelDetail.getLast_update_time() * 1000L, Common.dateFormat));

                    novel_detail_intro.setText(Html.fromHtml(novelDetail.getIntroduction()));

                    novel_detail_jindu1.setText(novelDetail.getLast_update_volume_name() + " " + novelDetail.getLast_update_chapter_name());
                    break;

            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.novel_detail_back:
                finish();
                break;
            case R.id.novel_detail_expand:
                ImageView v1 = (ImageView) v;
                if (novel_detail_intro.getLineCount() == 1) {
                    novel_detail_intro.setSingleLine(false);
                    v1.setImageResource(R.mipmap.img_close_btn);
                } else {
                    novel_detail_intro.setSingleLine(true);
                    v1.setImageResource(R.mipmap.img_open_btn);
                }
                break;
            case R.id.novel_detail_read:
                Intent intent = new Intent(NovelDetailActivity.this, NovelChapterActivity.class);
                intent.putExtra("id", novelDetail.getId());
                startActivity(intent);
                break;
            case R.id.novel_more_comment:
                break;
            case R.id.tv_novel_share:
                mController.openShare(this, false);
                break;
        }
    }
}
