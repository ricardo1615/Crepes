package com.lenovo.crepes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lenovo.crepes.adapters.Novels.NovelChapterAdapter2;
import com.lenovo.crepes.entities.ArrayListEvent;
import com.lenovo.crepes.entities.NovelChapter;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class NovelChapterActivity extends AppCompatActivity {
    private String chapterUrl = "http://v2.api.dmzj.com/novel/chapter/";
    private static final int NOVELCHAPTER = 200;
    private ListView lv_novel_chapter;
    private ArrayList<NovelChapter.ChaptersEntity> list;
    private int vid;
    private boolean isCanSend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_chapter);

        lv_novel_chapter = (ListView) findViewById(R.id.lv_novel_chapter);

        final int novelid = getIntent().getIntExtra("id", -1);
        vid = getIntent().getIntExtra("vid", -1);

        if (novelid != -1) {
            MyHttpUtils.sendDataArray(chapterUrl + novelid + ".json", handler, new NovelChapter(), NOVELCHAPTER);
        }

        lv_novel_chapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).getChapter_id() != -1) {
                    Intent intent = new Intent(NovelChapterActivity.this, NovelCotentActivity.class);
                    isCanSend = true;
                    intent.putExtra("position", position);
                    intent.putExtra("novelid", novelid);
                    int volumeId = list.get(position).getP_volume_id();
                    int chapterId = list.get(position).getChapter_id();
                    String urlId = novelid + "_" + volumeId + "_" + chapterId;
                    String filePath = novelid + "/" + volumeId + "/" + chapterId;
                    intent.putExtra("urlId", urlId);
                    intent.putExtra("filePath", filePath);
                    NovelChapterActivity.this.startActivity(intent);
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NOVELCHAPTER:
                    List<NovelChapter> obj = (List) msg.obj;
                    NovelChapterAdapter2 adapter2 = new NovelChapterAdapter2(NovelChapterActivity.this, formatChapter(obj), R.layout.item_novel_chapter);
                    lv_novel_chapter.setAdapter(adapter2);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getP_volume_id() == vid) {
                            lv_novel_chapter.setSelection(i);
                            break;
                        }
                    }
                    break;
            }
        }
    };

    @Override
    protected void onStop() {
        if (isCanSend) {
            EventBus.getDefault().post(new ArrayListEvent(list));
            isCanSend = false;
        }
        super.onStop();
    }

    private List<NovelChapter.ChaptersEntity> formatChapter(List<NovelChapter> obj) {
        list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            NovelChapter.ChaptersEntity chaptersEntity = new NovelChapter.ChaptersEntity();
            chaptersEntity.setP_volume_id(obj.get(i).getVolume_id());
            chaptersEntity.setP_volume_name("<" + obj.get(i).getVolume_name() + ">");
            chaptersEntity.setP_volume_order(obj.get(i).getVolume_order());
            chaptersEntity.setChapter_id(-1);
            list.add(chaptersEntity);

            List<NovelChapter.ChaptersEntity> entityList = obj.get(i).getChapters();

            for (int j = 0; j < entityList.size(); j++) {
                entityList.get(j).setP_volume_id(obj.get(i).getVolume_id());
                entityList.get(j).setP_volume_name(obj.get(i).getVolume_name());
                entityList.get(j).setP_volume_order(obj.get(i).getVolume_order());
                list.add(entityList.get(j));
            }
        }
        return list;
    }
}
