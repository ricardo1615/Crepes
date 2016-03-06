package com.lenovo.crepes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.crepes.adapters.ChapterAdapter;
import com.lenovo.crepes.app.MyApp;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.CartDetail;
import com.lenovo.crepes.utils.DataTransUtils;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.LinkedList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class CartDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private final  int REQUEST=100;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REQUEST:
                    cartDetail = (CartDetail) msg.obj;
                    Log.e("AAAA",cartDetail.toString());
                    dialog.dismiss();
                    initVIew();
                    break;
            }
        }
    };
    private CartDetail cartDetail;
    private TextView cart_detail_intro;
    private ProgressDialog dialog;
    private ImageView cart_detail_expand;
    private ChapterAdapter adapter;
    private List<CartDetail.ChaptersEntity.DataEntity> chapters;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.e("AAAA", id +"");
        dialog=new ProgressDialog(this);
        dialog.show();
        MyHttpUtils.sendDataOfGet(Common.CartDetailUrlhead + id + Common.CartDetailUrlfoot, handler, new CartDetail(), REQUEST);
    }

    private void initVIew() {
        //返回键
        ImageView cart_detail_back = (ImageView) findViewById(R.id.cart_detail_back);
        cart_detail_back.setOnClickListener(this);
        TextView cart_detail_title = (TextView) findViewById(R.id.cart_detail_title);
        cart_detail_title.setText(cartDetail.getTitle());
        ImageView cart_detail_img = (ImageView) findViewById(R.id.cart_detail_img);
        MyApp.getMybitmapUtils().display(cart_detail_img,cartDetail.getCover());
        TextView cart_detail_name1 = (TextView) findViewById(R.id.cart_detail_name1);
        TextView cart_detail_name2 = (TextView) findViewById(R.id.cart_detail_name2);
        TextView [] cart_detail_names=new TextView[]{cart_detail_name1,cart_detail_name2};
        List<CartDetail.AuthorsEntity> authors = cartDetail.getAuthors();
        int i=0;
        for ( i = 0; i < authors.size(); i++) {
            if(null!=authors.get(i)) {
                cart_detail_names[i].setText(authors.get(i).getTag_name());
            }
        }
        for (int j = cart_detail_names.length-1; j > i-1; j--) {
            cart_detail_names[j].setVisibility(View.GONE);
        }
        TextView cart_detail_type1 = (TextView) findViewById(R.id.cart_detail_type1);
        TextView cart_detail_type2 = (TextView) findViewById(R.id.cart_detail_type2);
        TextView cart_detail_type3 = (TextView) findViewById(R.id.cart_detail_type3);
        TextView[] cart_detail_types=new TextView[]{cart_detail_type1,cart_detail_type2,cart_detail_type3};
        List<CartDetail.TypesEntity> types = cartDetail.getTypes();
        for ( i = 0; i < types.size(); i++) {
            if(null!=types.get(i)) {
                cart_detail_types[i].setText(types.get(i).getTag_name());
            }
        }
        for (int j = cart_detail_names.length-1; j > i-1; j--) {
            cart_detail_types[j].setVisibility(View.GONE);
        }
        TextView cart_detail_renqi = (TextView) findViewById(R.id.cart_detail_renqi);
        cart_detail_renqi.setText(cartDetail.getHot_num()+"");
        TextView cart_detail_dingyueNum = (TextView) findViewById(R.id.cart_detail_dingyueNum);
        cart_detail_dingyueNum.setText(cartDetail.getSubscribe_num()+"");
        TextView cart_detail_time = (TextView) findViewById(R.id.cart_detail_time);
        cart_detail_time.setText(DataTransUtils.transdata(cartDetail.getLast_updatetime() * 1000l, "yy-MM-dd"));
        TextView cart_detail_jindu = (TextView) findViewById(R.id.cart_detail_jindu);
        cart_detail_jindu.setText(cartDetail.getStatus().get(0).getTag_name());
        Button cart_detail_dingyue = (Button) findViewById(R.id.cart_detail_dingyue);
        Button cart_detail_read = (Button) findViewById(R.id.cart_detail_read);
        cart_detail_dingyue.setOnClickListener(this);
        cart_detail_read.setOnClickListener(this);
        cart_detail_intro = (TextView) findViewById(R.id.cart_detail_intro);
        cart_detail_intro.setText(cartDetail.getDescription());
        cart_detail_expand = (ImageView) findViewById(R.id.cart_detail_expand);
        cart_detail_expand.setOnClickListener(this);
        TextView cart_detail_jindu1 = (TextView) findViewById(R.id.cart_detail_jindu1);
        cart_detail_jindu1.setText(cartDetail.getTypes().get(0).getTag_name());
        GridView cart_detail_cap = (GridView) findViewById(R.id.cart_detail_cap);
        chapters = cartDetail.getChapters().get(0).getData();
        List<CartDetail.ChaptersEntity.DataEntity> list=new LinkedList<>();
        Log.e("AAAA", chapters.size() + "");
        if(chapters.size()>8){
            for ( i = 0; i < 7; i++) {
                list.add(chapters.get(i));
                Log.e("AAAA", "chapter" + i);
            }
            CartDetail.ChaptersEntity.DataEntity chapter=new CartDetail.ChaptersEntity.DataEntity();
            chapter.setChapter_title("....");
            chapter.setChapter_id(-1);
            list.add(chapter);
        }else {
            for ( i = 0; i < chapters.size(); i++) {
                list.add(chapters.get(i));
                Log.e("AAAA","chapter"+i);
            }
        }
        adapter = new ChapterAdapter(this,list, R.layout.cart_details_item);
        cart_detail_cap.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cart_detail_back://fanhui
                finish();
                break;
            case R.id.cart_detail_dingyue://订阅或取消订阅

                break;
            case R.id.cart_detail_read://阅读
                Intent intent=new Intent(CartDetailActivity.this,ReadCartActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("chapterId",chapters.get(0).getChapter_id()+"");
                startActivity(intent);
                break;
            case R.id.cart_detail_expand://折叠或展开介绍
                if (cart_detail_intro.getLineCount()==1){
                    cart_detail_intro.setSingleLine(false);
                    cart_detail_expand.setImageResource(R.mipmap.img_close_btn);
                }else {
                    cart_detail_intro.setSingleLine(true);
                    cart_detail_expand.setImageResource(R.mipmap.img_open_btn);
                }
                break;
        }
    }
    @Subscribe
    public void hehe(String [] strings){
        Log.e("AAAA", "hehe CartDetailActivity");
        if(strings[0].equals("expand")){
            adapter.setData(chapters);
            adapter.notifyDataSetChanged();
        }
        if(strings[0].equals("read")){
            Intent intent=new Intent(CartDetailActivity.this,ReadCartActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("chapterId", strings[1] + "");
            startActivity(intent);
        }
    }



    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
