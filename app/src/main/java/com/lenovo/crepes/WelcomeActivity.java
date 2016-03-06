package com.lenovo.crepes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lenovo.crepes.adapters.CustomerPagerAdapter;
import com.lenovo.crepes.utils.PackageUtils;

import java.util.LinkedList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private int [] images=new int[]{R.mipmap.img_guide_p1,R.mipmap.img_guide_p2,R.mipmap.img_guide_p3};
    private int [] colors=new int[]{Color.argb(170,255,255,0),Color.argb(170,0,0,255),Color.argb(255,153,50,204)};
    private int [] images2 =new int[]{R.mipmap.img_guide_w1,R.mipmap.img_guide_w2,R.mipmap.img_guide_w3};
    private List<View> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SharedPreferences sp = getSharedPreferences("version", MODE_PRIVATE);
        String version = sp.getString("version", null);
        String packageVersion = PackageUtils.getPackageVersion(this);

        if (packageVersion.equals(version)){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        viewPager = (ViewPager) findViewById(R.id.vp);
        getImageView();
        CustomerPagerAdapter<R> adapter=new CustomerPagerAdapter<R>(list);
        viewPager.setAdapter(adapter);
    }

    public void getImageView() {
        list=new LinkedList<>();
        for (int i=0;i<images.length;i++){
            View view = View.inflate(this, R.layout.welcome_item, null);
            view.setBackgroundColor(colors[i]);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            image.setImageResource(images[i]);
            ImageView head = (ImageView) view.findViewById(R.id.head);
            head.setImageResource(images2[i]);

            ImageView image_btn = (ImageView) view.findViewById(R.id.image_btn);
            if (i==2){
                image_btn.setVisibility(View.VISIBLE);
                image_btn.setOnClickListener(this);
            }else {
                image_btn.setImageBitmap(null);
            }
            list.add(view);
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,MainActivity.class));
        SharedPreferences sp = getSharedPreferences("version", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("version", PackageUtils.getPackageVersion(this));
        edit.apply();
        finish();
    }
}
