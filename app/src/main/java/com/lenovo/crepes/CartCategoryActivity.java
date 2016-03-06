package com.lenovo.crepes;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lenovo.crepes.adapters.CartCategoryResultAdapter;
import com.lenovo.crepes.adapters.ClassifyFilterAdapter;
import com.lenovo.crepes.common.Common;
import com.lenovo.crepes.entities.CartCategoryResult;
import com.lenovo.crepes.entities.ClassifyFilter;
import com.lenovo.crepes.utils.MyHttpUtils;

import java.util.LinkedList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class CartCategoryActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private final int FILTER = 100;
    private final int RESULT = 200;
    private GridView category_gridview;//选择项
    private PullToRefreshGridView category_gridview_jieguo;//结果
    private ClassifyFilterAdapter adapter;
    private List<ClassifyFilter> list;//题材。。。
    private CheckBox category_ticai;
    private CheckBox category_duzhequn;
    private CheckBox category_jindu;
    private CheckBox category_diyu;
    private final int category_ticai_id = 1001;
    private final  int category_duzhequn_id = 1002;
    private final int category_jindu_id = 1003;
    private final int category_diyu_id = 1004;
    private int type = -1;
    private String category_ticai_str;
    private String category_duzhequn_str;
    private String category_jindu_str;
    private String category_diyu_str;
    private int item=0;
    private LinkedList<ClassifyFilter.ItemsEntity> itemsEntities;
    private StringBuffer buffer;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_category);
        name = getIntent().getStringExtra("id");
        initview();
        EventBus.getDefault().register(this);
    }

    private void initview() {
        buffer = new StringBuffer();
        ImageView cart_two_back = (ImageView) findViewById(R.id.cart_two_back);
        category_ticai = (CheckBox) findViewById(R.id.category_ticai);
        category_duzhequn = (CheckBox) findViewById(R.id.category_duzhequn);
        category_jindu = (CheckBox) findViewById(R.id.category_jindu);
        category_diyu = (CheckBox) findViewById(R.id.category_diyu);
        category_ticai.setOnClickListener(this);
        category_duzhequn.setOnClickListener(this);
        category_jindu.setOnClickListener(this);
        category_diyu.setOnClickListener(this);
        removefocus();
        RadioGroup category_radiogroup2 = (RadioGroup) findViewById(R.id.category_radiogroup2);
        RadioButton category_renqi = (RadioButton) findViewById(R.id.category_renqi);
        RadioButton category_gengxin = (RadioButton) findViewById(R.id.category_gengxin);
        category_renqi.setOnClickListener(this);
        category_gengxin.setOnClickListener(this);
        category_gridview = (GridView) findViewById(R.id.category_gridview);
        category_gridview_jieguo = (PullToRefreshGridView) findViewById(R.id.category_gridview_jieguo);
        category_gridview_jieguo.getRefreshableView().setNumColumns(3);
        cart_two_back.setOnClickListener(this);
        category_radiogroup2.setOnCheckedChangeListener(this);
        MyHttpUtils.sendDataArray(Common.categoryfilter, handler, new ClassifyFilter(), FILTER);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FILTER:
                    list = (List<ClassifyFilter>) msg.obj;
                    check(name);
                    MyHttpUtils.sendDataArray(getUrl(buffer),handler,new CartCategoryResult(),RESULT);
                    itemsEntities = new LinkedList<>();
                    adapter = new ClassifyFilterAdapter(CartCategoryActivity.this, itemsEntities, R.layout.fragment_category_classifyfilter_item);
                    type = category_ticai_id;
                    category_gridview.setAdapter(adapter);
                    break;
                case RESULT:
                    List<CartCategoryResult> list= (List<CartCategoryResult>) msg.obj;
                    CartCategoryResultAdapter adapter=new CartCategoryResultAdapter(CartCategoryActivity.this,list,R.layout.fragment_cart_item);
                    category_gridview_jieguo.setAdapter(adapter);
                    break;
            }
        }
    };

    public void check(String name){
        List<ClassifyFilter.ItemsEntity> itemsEntity1 = list.get(0).getItems();
        for (int i = 0; i < itemsEntity1.size(); i++) {
            if (name.equals(itemsEntity1.get(i).getTag_name())){
                category_ticai.setText(name);
                category_ticai_str=itemsEntity1.get(i).getTag_id()+"";
                break;
            }
        }
        List<ClassifyFilter.ItemsEntity> itemsEntity2 = list.get(1).getItems();
        for (int i = 0; i < itemsEntity2.size(); i++) {
            if (name.equals(itemsEntity2.get(i).getTag_name())){
                category_ticai.setText(name);
                category_ticai_str=itemsEntity2.get(i).getTag_id()+"";
                break;
            }
        }
        List<ClassifyFilter.ItemsEntity> itemsEntity3 = list.get(2).getItems();
        for (int i = 0; i < itemsEntity3.size(); i++) {
            if (name.equals(itemsEntity3.get(i).getTag_name())){
                category_ticai.setText(name);
                category_ticai_str=itemsEntity3.get(i).getTag_id()+"";
                break;
            }
        }
        List<ClassifyFilter.ItemsEntity> itemsEntity4 = list.get(3).getItems();
        for (int i = 0; i < itemsEntity4.size(); i++) {
            if (name.equals(itemsEntity4.get(i).getTag_name())){
                category_ticai.setText(name);
                category_ticai_str=itemsEntity4.get(i).getTag_id()+"";
                break;
            }
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cart_two_back://返回
                finish();
                break;
            case R.id.category_ticai://题材
                if (adapter.getCount()!=0) {
//                    category_gridview.setVisibility(View.GONE);
                    adapter.setData(itemsEntities);
                    type=-1;
                } else {
//                    category_gridview.setVisibility(View.VISIBLE);
                    adapter.setData(list.get(0).getItems());
                    setChecked();
                    category_ticai.setChecked(true);
                    type = category_ticai_id;
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.category_duzhequn://读者群
                if (adapter.getCount()!=0) {
//                    category_gridview.setVisibility(View.GONE);
                    adapter.setData(itemsEntities);
                    type=-1;
                } else {
                    adapter.setData(list.get(1).getItems());
//                    category_gridview.setVisibility(View.VISIBLE);
                    setChecked();
                    category_duzhequn.setChecked(true);
                    type = category_duzhequn_id;
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.category_jindu://进度
                if (adapter.getCount()!=0) {
//                    category_gridview.setVisibility(View.GONE);
                    adapter.setData(itemsEntities);
                    type=-1;
                } else {
                    adapter.setData(list.get(2).getItems());
//                    category_gridview.setVisibility(View.VISIBLE);
                    setChecked();
                    category_jindu.setChecked(true);
                    type = category_jindu_id;
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.category_diyu://地域
                if (adapter.getCount()!=0) {
//                    category_gridview.setVisibility(View.GONE);
                    adapter.setData(itemsEntities);
                    type=-1;
                } else {
                    adapter.setData(list.get(3).getItems());
//                    category_gridview.setVisibility(View.VISIBLE);
                    setChecked();
                    category_diyu.setChecked(true);
                    type = category_diyu_id;
                }
                adapter.notifyDataSetChanged();
                break;

        }
    }



    private void removefocus(){
        category_ticai.setFocusable(false);
        category_duzhequn.setFocusable(false);
        category_jindu.setFocusable(false);
        category_diyu.setFocusable(false);
    }

    public void setChecked() {
        switch (type){
            case category_ticai_id:
                category_ticai.setChecked(false);
                break;
            case category_duzhequn_id:
                category_duzhequn.setChecked(false);
                break;
            case category_jindu_id:
                category_jindu.setChecked(false);
                break;
            case category_diyu_id:
                category_diyu.setChecked(false);
                break;
        }
        category_gridview.setFocusable(false);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.category_renqi://人气
                type=-1;
                item=0;
                setChecked();
                Toast.makeText(this, getUrl(buffer), Toast.LENGTH_LONG).show();
                break;
            case R.id.category_gengxin://更新
                type=-1;
                item=1;
                setChecked();
                Toast.makeText(this, getUrl(buffer), Toast.LENGTH_LONG).show();
                break;
        }
    }

    public String getUrl(StringBuffer buffer){
        buffer.delete(0,buffer.length());
        buffer.append(Common.categoryfilterResulthead);
        if (null!=category_ticai_str&&!"0".equals(category_ticai_str)){
            buffer.append(category_ticai_str).append("-");
        }
        if (null!=category_duzhequn_str&&!"0".equals(category_duzhequn_str)){
            buffer.append(category_duzhequn_str).append("-");
        }
        if (null!=category_jindu_str&&!"0".equals(category_jindu_str)){
            buffer.append(category_jindu_str).append("-");
        }
        if (null!=category_diyu_str&&!"0".equals(category_diyu_str)){
            buffer.append(category_diyu_str).append("-");
        }
        String s = buffer.toString();
        if (s.charAt(s.length()-1)=='-'){
            buffer.deleteCharAt(s.length()-1);
        }else {
            buffer.append("0");
        }
        buffer.append("/").append(item).append(Common.categoryfilterResultfoot);
        return buffer.toString();
    }

    @Subscribe
    public void hehe(String string) {
        if (type != -1) {
            if (type == category_ticai_id) {
                category_ticai.setText(string);
                category_ticai_str=string;
            } else if (type == category_duzhequn_id) {
                category_duzhequn.setText(string);
                category_duzhequn_str=string;
            } else if (type == category_jindu_id) {
                category_jindu.setText(string);
                category_jindu_str=string;
            } else if (type == category_diyu_id) {
                category_diyu.setText(string);
                category_diyu_str=string;
            }
            Toast.makeText(this, getUrl(buffer), Toast.LENGTH_LONG).show();
            setChecked();
            type=-1;
            adapter.setData(itemsEntities);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "数据还没回来，请稍等。", Toast.LENGTH_LONG).show();
        }
        category_gridview.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
