package com.lenovo.crepes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.customerView.CharsetDetector;
import com.lenovo.crepes.customerView.LoadingDialog;
import com.lenovo.crepes.customerView.ReadView;
import com.lenovo.crepes.entities.ArrayListEvent;
import com.lenovo.crepes.entities.NovelChapter;
import com.lenovo.crepes.utils.ScreenBrightUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class NovelCotentActivity extends AppCompatActivity implements View.OnClickListener {
    private String url = "http://v2.api.dmzj.com/novel/download/";
    private ReadView tv_novel_content;
    private List<NovelChapter.ChaptersEntity> list;
    private int position;
    private int pagePosition;
    private int novelid;
    private String filePathHead;
    private boolean menuVisibility;
    //    private PullToRefreshScrollView pullscroll_novel_content;
    private LinearLayout relativeLayout;
    private PowerManager.WakeLock wakeLock;
    private boolean isAuto = false;
    private CharBuffer buffer;
    private List<String> novelContent;
    private List<Integer> pageList;
    int size = 3;
    private int i = 1;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_cotent);

        tv_novel_content = (ReadView) findViewById(R.id.tv_novel_content);
        tv_novel_content.setTextSize(Common.textSizes[3]);

        novelContent = new ArrayList<>();
        pageList = new ArrayList<>();

        wakeLock = ((PowerManager) getSystemService(this.POWER_SERVICE)).newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        wakeLock.acquire();

        if (ScreenBrightUtils.isAutoBrightness(getContentResolver())) {
            isAuto = true;
            ScreenBrightUtils.stopAutoBrightness(getContentResolver());
        }
        EventBus.getDefault().register(this);
        SeekBar sb_bright = (SeekBar) findViewById(R.id.sb_bright);
        sb_bright.setMax(175);
        sb_bright.setProgress(ScreenBrightUtils.getBrightness(getContentResolver()));
        sb_bright.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ScreenBrightUtils.setBrightness(NovelCotentActivity.this, progress + 80);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        relativeLayout = (LinearLayout) findViewById(R.id.novel_content_menu);
        RadioGroup rg_novel_background = (RadioGroup) findViewById(R.id.rg_novel_background);
        rg_novel_background.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_day:
                        tv_novel_content.setTextColor(Color.BLACK);
                        tv_novel_content.setBackgroundColor(Color.WHITE);
                        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                        break;
                    case R.id.rb_night:
                        tv_novel_content.setTextColor(Color.WHITE);
                        tv_novel_content.setBackgroundColor(Color.BLACK);
                        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                        break;
                    case R.id.rb_eyes:
                        tv_novel_content.setTextColor(Color.BLACK);
                        tv_novel_content.setBackgroundColor(Color.parseColor("#cce8cf"));
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#cce8cf"));
                        break;
                }
            }
        });
        rg_novel_background.check(R.id.rb_day);

        findViewById(R.id.btn_text_size_up).setOnClickListener(this);
        findViewById(R.id.btn_text_size_down).setOnClickListener(this);

        Intent intent = getIntent();

        position = intent.getIntExtra("position", -1);
        novelid = intent.getIntExtra("novelid", -1);
        String urlId = intent.getStringExtra("urlId");
        String filePath = intent.getStringExtra("filePath");
        filePathHead = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/CrepesNovel";


        filePath = filePathHead + "/" + filePath + ".txt";
        File file = new File(filePath);
        if (file.exists()) {
            displayNovelContent(file);
        } else {
            getNovelContent(urlId, filePath);
        }
    }

    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            x1 = ev.getX();
            y1 = ev.getY();

        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            x2 = ev.getX();
            y2 = ev.getY();

            if (x1 == x2 && y1 == y2) {//点击翻页
                int height = getWindowManager().getDefaultDisplay().getHeight();
                int width = getWindowManager().getDefaultDisplay().getWidth();
                if (x1 < width / 3 * 2 && x1 > width / 3 && y1 < height / 3 * 2 && y1 > height / 3) {
                    if (menuVisibility) {//隐藏菜单
                        relativeLayout.setVisibility(View.GONE);
                        menuVisibility = false;
                    } else {//显示菜单
                        relativeLayout.setVisibility(View.VISIBLE);
                        menuVisibility = true;
                    }
                } else if (!menuVisibility && (x1 > width / 3 * 2 || y1 > height / 3 * 2)) {//下一页
                    moveToNextPage();
                } else if (!menuVisibility && (x1 < width / 3 || y1 < height / 3)) {//上一页
                    moveToPreviousPage();
                }
            } else if (!menuVisibility) {//滑动翻页
                if ((x2 - x1) < 0) {
                    moveToNextPage();
                } else if ((x2 - x1) > 0) {
                    moveToPreviousPage();
                }
            }
            x1 = x2 = y1 = y2 = 0;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 向前翻页
     */
    private void moveToPreviousPage() {
        if (pageList.size() > 0) {
            if (i > 1) {
                i--;
                loadPage(pageList.get(i - 1));
                tv_novel_content.resize();
            } else {
                if (position > 1) {
                    position--;
                    sendNovelContentRequest(0);
                } else {
                    Toast.makeText(this, "前方空空如也", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * 向后翻页
     */
    private void moveToNextPage() {
        if (pageList.size() > i) {
            i++;
            loadPage(pageList.get(i - 1));
            tv_novel_content.resize();
        } else if (buffer.length() > tv_novel_content.getCharNum()) {
            i++;
            pagePosition += tv_novel_content.getCharNum();
            pageList.add(pagePosition);
            loadPage(pagePosition);
            tv_novel_content.resize();
        } else if (buffer.length() <= tv_novel_content.getCharNum()) {
            if (position < list.size()) {
                position++;
                sendNovelContentRequest(1);
            } else {
                Toast.makeText(this, "前方空空如也", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 编辑并发送请求
     */
    private void sendNovelContentRequest(int to) {

        int chapterId = list.get(position).getChapter_id();
        if (chapterId == -1) {
            if (to == 0 && position > 1) {
                position--;
            } else if (to == 1 && position < list.size()) {
                position++;
            } else {
                Toast.makeText(this, "前方空空如也", Toast.LENGTH_LONG).show();
                return;
            }
            chapterId = list.get(position).getChapter_id();
        }
        int volumeId = list.get(position).getP_volume_id();
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.show(getSupportFragmentManager(), "NovelCotentActivity");
        String filePath = novelid + "/" + volumeId + "/" + chapterId;
        filePath = filePathHead + "/" + filePath + ".txt";

        File file = new File(filePath);
        if (file.exists()) {
            displayNovelContent(file);
            return;
        }

        String urlId = novelid + "_" + volumeId + "_" + chapterId;
        getNovelContent(urlId, filePath);
    }

    private void displayNovelContent(File file) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        loadBook(file);
        loadPage(0);
        pageList.add(0);
    }

    private void loadPage(int position) {
        buffer.position(position);
        tv_novel_content.setText(buffer);
    }

    private void loadBook(File file) {
        FileInputStream in = null;
        BufferedReader reader = null;
        try {
            in = new FileInputStream(file);
            Charset charset = CharsetDetector.detect(in);
            reader = new BufferedReader(new InputStreamReader(in, charset));
            StringBuffer stringBuffer = new StringBuffer();
            String str = new String();
            while ((str = reader.readLine()) != null) {
                stringBuffer.append(str);
                stringBuffer.append("\r\n");
            }
            str = stringBuffer.toString();
            buffer = CharBuffer.wrap(str.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    * 文本文件中的Html标签去掉
    * */
    private void formatHtmlToTxt(File result) {
        BufferedReader bufferedReader = null;
        BufferedWriter writer = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(result)));
            StringBuffer buffer = new StringBuffer();
            String str = new String();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            str = Html.fromHtml(buffer.toString()).toString();
            if (str != null) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(result)));
                writer.write(str, 0, str.length());
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Subscribe
    public void onEventMainThread(ArrayListEvent event) {
        list = new ArrayList<>();
        list.addAll(event.getList());
    }

    @Override
    protected void onDestroy() {
        if (isAuto) {
            ScreenBrightUtils.startAutoBrightness(getContentResolver());
        }
        EventBus.getDefault().unregister(this);
        wakeLock.release();
        super.onDestroy();
    }

    private void getNovelContent(String urlId, String filePath) {
        MyApp.getMyhttpUtils().download(url + urlId + ".txt", filePath, new RequestCallBack<File>() {
                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        formatHtmlToTxt(responseInfo.result);
                        displayNovelContent(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Toast.makeText(NovelCotentActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                    }
                }

        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_text_size_up:
                size++;
                break;
            case R.id.btn_text_size_down:
                size--;
                break;
        }
        if (size >= 0 && size <= 6) {
//            MyApp.getUserData().setTextSizeIndex(size);
            tv_novel_content.setTextSize(Common.textSizes[size]);
        }
    }
}
