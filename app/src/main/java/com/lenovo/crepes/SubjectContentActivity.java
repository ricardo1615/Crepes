package com.lenovo.crepes;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.crepes.adapters.SubjectContentAdapter;
import com.lenovo.crepes.base.ImageAsyncTask;
import com.lenovo.crepes.base.ListViewForScrollView;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.SubjectDetail;
import com.lenovo.crepes.entities.SubjectList;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubjectContentActivity extends AppCompatActivity implements View.OnClickListener {

    private SubjectList subjectList;
    private TextView tvSubjectDescription;
    private SubjectContentAdapter adapter;
    private ListViewForScrollView listViewForScrollView;
    private List<SubjectDetail.ComicsEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_content);
        subjectList = (SubjectList) getIntent().getSerializableExtra("SubjectList");
        init();
    }

    private void init() {

        list = new ArrayList<>();

        MyHttpUtils.sendDataOfGet(Common.subjectUrlHead + "/"+subjectList.getId() + Common.CartDetailUrlfoot, handler, new SubjectDetail(), 100);

        ImageView iv_subject_cover = (ImageView) findViewById(R.id.iv_subject_cover);
        iv_subject_cover.setTag(subjectList.getSmall_cover());
        new ImageAsyncTask(iv_subject_cover, 4).execute(subjectList.getSmall_cover());

        ((TextView) findViewById(R.id.subject_content_title)).setText(subjectList.getShort_title());
        ((TextView) findViewById(R.id.tv_subject_name)).setText(subjectList.getTitle());
        tvSubjectDescription = (TextView) findViewById(R.id.tv_subject_description);

        findViewById(R.id.subject_content_back).setOnClickListener(this);

        listViewForScrollView = (ListViewForScrollView) findViewById(R.id.lv_subject_content);
        listViewForScrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list != null && list.size() > 0) {

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subject_content_back:
                finish();
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    SubjectDetail subjectDetail = (SubjectDetail) msg.obj;
                    tvSubjectDescription.setText(subjectDetail.getDescription());
                    if (subjectDetail.getComics() != null) {
                        list.addAll(subjectDetail.getComics());
                        adapter = new SubjectContentAdapter(SubjectContentActivity.this, list, R.layout.item_subject_content);
                        listViewForScrollView.setAdapter(adapter);
                    }
                    break;
            }
        }
    };
}
