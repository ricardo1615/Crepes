package com.lenovo.crepes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.crepes.adapters.ReadCartAdapter;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.CartDetail;
import com.lenovo.crepes.entities.ChapterMeg;
import com.lenovo.crepes.utils.LogUtils;
import com.lenovo.crepes.utils.MyHttpUtils;
import com.lenovo.crepes.utils.NetworkUtils;

import java.util.LinkedList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class ReadCartActivity extends AppCompatActivity implements View.OnClickListener {
    private final int REQUEST = 100;
    private final int REQUESTOFChapter = 200;
    private List<CartDetail.ChaptersEntity.DataEntity> data;
    private String id;
    private String chapterId;
    private RecyclerView read_recyclerview;
    private ReadCartAdapter adapter;
    private CartDetail cartDetail;
    private List<String> list;
    private LinearLayoutManager linearLayoutManager;
    private MyAsyncTask myAsyncTask;
    private int width;
    private int height;
    private int firstVisibleItemPosition;
    private boolean trans;
    private int i;
    private View read_top;
    private View read_bottom;
    private boolean menushowing;
    private CartDetail.ChaptersEntity.DataEntity chapterMsg;
    private TextView read_title;
    private int index;
    private int chapter_index;
    private SeekBar read_seekbar;
    private TextView yeshu;
    private int picNum = -1;

    private boolean canLoad;
    private View flash;
    private ChapterMeg chapterMegs;
    private LinearLayout menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_cart);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        width = defaultDisplay.getWidth();
        height = defaultDisplay.getHeight();
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        chapterId = intent.getStringExtra("chapterId");
        flash = findViewById(R.id.flash);
        AnimationDrawable background = (AnimationDrawable) flash.getBackground();
        background.start();
        //头
        read_top = findViewById(R.id.read_top);
        //返回
        ImageView read_back = (ImageView) findViewById(R.id.read_back);
        read_back.setOnClickListener(this);
        //章节标题
        read_title = (TextView) findViewById(R.id.read_title);
        //操作
        TextView read_operator = (TextView) findViewById(R.id.read_operator);
        menus = (LinearLayout) findViewById(R.id.menus);
        read_operator.setOnClickListener(this);
        //foot
        read_bottom = findViewById(R.id.read_bottom);
        //seekbar
        read_seekbar = (SeekBar) findViewById(R.id.read_seekbar);
        read_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                read_recyclerview.scrollToPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //信息 1/8页
        yeshu = (TextView) findViewById(R.id.yeshu);
        //选项
        RadioButton read_options = (RadioButton) findViewById(R.id.read_options);
        read_options.setOnClickListener(this);
        //左手模式
        RadioButton read_left = (RadioButton) findViewById(R.id.read_left);
        read_left.setOnClickListener(this);
        //分享
        RadioButton read_share = (RadioButton) findViewById(R.id.read_share);
        read_share.setOnClickListener(this);
        //吐槽
        RadioButton read_discuss = (RadioButton) findViewById(R.id.read_discuss);
        read_discuss.setOnClickListener(this);


        read_recyclerview = (RecyclerView) findViewById(R.id.read_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        read_recyclerview.setLayoutManager(linearLayoutManager);
        read_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                View firstview = recyclerView.getChildAt(0);
                firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                int itemwidth = firstview.getWidth();
                int firstItemRight = linearLayoutManager.getDecoratedRight(firstview);
//                i = (firstVisibleItemPosition - 1) * itemwidth - firstItemRight;
//                Log.e("AAAA", "dx=" + dx + "dy" + dy + "firstVisibleItemPosition=" + firstVisibleItemPosition);
//                Log.e("AAAA","距离是"+ i +"宽度："+width);

                if (firstItemRight < width / 2) {
                    trans = true;
                    if (firstVisibleItemPosition == 0 && firstItemRight > width) {
                        Log.e("AAAA", "撞顶了");
                    }
                    if (firstVisibleItemPosition == linearLayoutManager.getItemCount() - 1 && firstItemRight < width) {
                        Log.e("AAAA", "结束了");
                    }
                } else {
                    trans = false;
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (trans) {
                        if (firstVisibleItemPosition < linearLayoutManager.getItemCount() - 1) {
                            recyclerView.scrollToPosition(firstVisibleItemPosition + 1);
                        }
                    } else {
                        recyclerView.scrollToPosition(firstVisibleItemPosition);
                    }
                }
            }
        });

        //请求本漫画的内容
        MyHttpUtils.sendDataOfGet(Common.CartDetailUrlhead + id + Common.CartDetailUrlfoot, handler, new CartDetail(), REQUEST);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REQUEST:
                    cartDetail = (CartDetail) msg.obj;
                    data = cartDetail.getChapters().get(0).getData();
                    //检索
                    check();
                    list = new LinkedList<>();
                    //请求章节内容
                    MyHttpUtils.sendDataOfGet(Common.ChapterMsgurlhead+id+"/"+chapterId+Common.ChapterMsgurlfoot,handler,new ChapterMeg(),REQUESTOFChapter);
//                    myAsyncTask = new MyAsyncTask();
//                    myAsyncTask.execute();
                    Log.e("AAAA", cartDetail.toString());
                    break;
                case REQUESTOFChapter:
                    chapterMegs = (ChapterMeg) msg.obj;
                    //图片个数
                    picNum = chapterMegs.getPage().size();
                    read_seekbar.setMax(picNum);
                    chapter_index = 0;
                    changedMeg();
                    if (null == adapter) {
                        adapter = new ReadCartAdapter(ReadCartActivity.this, chapterMegs.getPage(), R.layout.read_item);
                        read_recyclerview.setAdapter(adapter);
                    } else {
                        adapter.setData(list);
                        adapter.notifyDataSetChanged();
                    }
                    canLoad = !canLoad;
                    flash.setVisibility(View.GONE);
                    SharedPreferences sharedPreferences = getSharedPreferences("menu", MODE_PRIVATE);
                    String string = sharedPreferences.getString("menu", "null");
                    if (string.equals("null")){
                        menus.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };

    //检测章节所在位置
    private void check() {
        for (int i = 0; i < data.size(); i++) {
            if (chapterId.equals(data.get(i).getChapter_id() + "")) {
                index = i;
                chapterMsg = data.get(i);
                //赋值
                read_title.setText(chapterMsg.getChapter_title());
                break;
            }
        }
    }

    public void changedMeg() {
        if (picNum != -1) {
            read_title.setText(chapterMsg.getChapter_title());
            read_seekbar.setProgress(chapter_index);
            yeshu.setText(chapter_index + "/" + picNum + "页");
        }

    }

    @Subscribe
    public void hehe(boolean b) {
        if (b) {
            Log.e("AAAA", "上");
        } else {
            Log.e("AAAA", "下");
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {//// FIXME: 2015/11/20
        switch (v.getId()) {
            case R.id.read_back://返回
                finish();
                break;
            case R.id.read_operator://操作（引导）
                menus.setVisibility(View.VISIBLE);
                break;
            case R.id.read_options://选项

                break;
            case R.id.read_left://左手模式

                break;
            case R.id.read_share://分享

                break;
            case R.id.read_discuss://吐槽

                break;
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, List<String>> {


        @Override
        protected List doInBackground(Void... params) {
            list.clear();
            for (int i = 0; i < 30; i++) {
                String str = Common.readUrlhead + cartDetail.getFirst_letter() + "/" + id + "/" + chapterId + "/" + i + Common.readUrlfoot;
                LogUtils.e("AAAA", "url" + str);
                boolean b = NetworkUtils.checkURL(str);
                if (b) {
                    list.add(str);
                } else {
                    break;
                }
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            Log.e("AAAA", "size" + strings.size() + "");
            //图片个数
            picNum = strings.size();
            read_seekbar.setMax(picNum);
            chapter_index = 0;
            changedMeg();
            if (null == adapter) {
                adapter = new ReadCartAdapter(ReadCartActivity.this, strings, R.layout.read_item);
                read_recyclerview.setAdapter(adapter);
            } else {
                adapter.setData(list);
                adapter.notifyDataSetChanged();
            }
            canLoad = !canLoad;
            flash.setVisibility(View.GONE);
        }
    }

    private float x1;
    private float y1;
    private float x2;
    private float y2;


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (menus.getVisibility()==View.VISIBLE){
            menus.setVisibility(View.GONE);
            SharedPreferences sharedPreferences = getSharedPreferences("menu", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("menu","true");
            edit.apply();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                Log.e("AAAA", "按下");
                x1 = ev.getX();
                y1 = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
//                Log.e("AAAA", "抬起");
                x2 = ev.getX();
                y2 = ev.getY();
                if (x1 == x2 && y1 == y2) {
                    int height = getWindowManager().getDefaultDisplay().getHeight();
                    int width = getWindowManager().getDefaultDisplay().getWidth();
//            Toast.makeText(ReadCartActivity.this, "dispatchTouchEvent点击事件x1=" + x1 + "y1:" + y1, Toast.LENGTH_SHORT).show();
                    if (x1 < width/3) {//左翻
                        if (menushowing) {
                            hidemenu();
                        } else {
                            operatorLeft();
                        }
                    } else if (x1 > width/3*2) {//右翻
                        if (menushowing) {
                            hidemenu();
                        } else {
                            operatorRight();
                        }
                    } else {
                        if (y1 < height/3) {//左翻
                            if (menushowing) {
                                hidemenu();
                            } else {
                                operatorLeft();
                            }
                        } else if (y1 > height/3*2) {//右翻
                            if (menushowing) {
                                hidemenu();
                            } else {
                                operatorRight();
                            }
                        } else {//唤出菜单
                            if (menushowing) {
                                hidemenu();
                            } else {
                                showmenu();
                            }
                        }
                    }
                }
                //防止点击同一处异常现象
                x1 = x2 = y1 = y2 = 0;
                break;
        }


        return super.dispatchTouchEvent(ev);
    }

    private void hidemenu() {
        read_bottom.setVisibility(View.GONE);
        read_top.setVisibility(View.GONE);
        menushowing = false;
    }

    private void showmenu() {
        read_bottom.setVisibility(View.VISIBLE);
        read_top.setVisibility(View.VISIBLE);
        menushowing = true;
    }

    private void operatorRight() {
        LogUtils.e("AAAA", "右");
        int position = linearLayoutManager.getPosition(read_recyclerview.getChildAt(0)) + 1;
        if (position != linearLayoutManager.getItemCount()) {
            read_recyclerview.scrollToPosition(position);
            chapter_index = position;
            changedMeg();
        } else {//请求下一章内容
            if (canLoad) {
                LogUtils.e("AAAA", "请求下一章内容");
                if (index == 0) {
                    Toast.makeText(this, "最后一章了", Toast.LENGTH_SHORT).show();
                } else {
                    chapterMsg = data.get(--index);
                    chapterId = chapterMsg.getChapter_id() + "";
                    //请求章节内容
                    MyHttpUtils.sendDataOfGet(Common.ChapterMsgurlhead + id + "/" + chapterId + Common.ChapterMsgurlfoot, handler, new ChapterMeg(), REQUESTOFChapter);
                    canLoad = !canLoad;
                }
            } else {
                Toast.makeText(ReadCartActivity.this, "正在请求，不要重复操作哦", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //左翻操作
    private void operatorLeft() {
        LogUtils.e("AAAA", "左");
        int position = linearLayoutManager.getPosition(read_recyclerview.getChildAt(0)) - 1;
        if (position != -1) {
            read_recyclerview.scrollToPosition(position);
            chapter_index = position;
            changedMeg();
        } else {//请求上一章内容
            if (canLoad) {
                LogUtils.e("AAAA", "请求上一章内容");
                if (index == data.size() - 1) {
                    Toast.makeText(this, "前面已经没有了哦", Toast.LENGTH_SHORT).show();
                } else {
                    chapterMsg = data.get(++index);
                    chapterId = chapterMsg.getChapter_id() + "";
                    //请求章节内容
                    MyHttpUtils.sendDataOfGet(Common.ChapterMsgurlhead + id + "/" + chapterId + Common.ChapterMsgurlfoot, handler, new ChapterMeg(), REQUESTOFChapter);
                    canLoad = !canLoad;
                }
            } else {
                Toast.makeText(ReadCartActivity.this, "正在请求，不要重复操作哦", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
