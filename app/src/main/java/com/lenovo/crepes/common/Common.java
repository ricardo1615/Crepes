package com.lenovo.crepes.common;

import com.lenovo.crepes.R;

/**
 * Created by Lenovo on 2015/11/10.
 */
public interface Common {
    //头部轮播器小圆点id
    int[] leads = {R.id.rb_head_one, R.id.rb_head_two, R.id.rb_head_three, R.id.rb_head_four, R.id.rb_head_five};

    //漫画推荐Url
    String recommend = "http://v2.api.dmzj.com/recommend.json";
    //猜你喜欢Url
    String tellmowhy = "http://v2.api.dmzj.com/recommend/batchUpdate?category_id=50";
    //更新Url
    String update = "http://v2.api.dmzj.com/latest/0.json";
    //分类Url
    String category = "http://v2.api.dmzj.com/0/category.json";
    //分类过滤Url
    String categoryfilter = "http://v2.api.dmzj.com/classify/filter.json";
    //分类过滤结果Url
    String categoryfilterResulthead = "http://v2.api.dmzj.com/classify/";//XXX-XXX-XXX-XXX/0/
    String categoryfilterResultfoot = "/0.json";

    //CartDetailUrl
    String CartDetailUrlhead = "http://v2.api.dmzj.com/comic/";
    String CartDetailUrlfoot = ".json";

    //ChapterMsg
    String ChapterMsgurlhead="http://api.dmzj.com/dynamic/comicread/";//8259/43668
    String ChapterMsgurlfoot=".json";
    //read
    String readUrlhead = "http://imgsmall.dmzj.com/";//    y/9949/43726/3
    String readUrlfoot = ".jpg";

    //时间格式（精确到天）
    String dateFormat = "yyyy-MM-dd";

    //评论头部
    String commentUrl = "http://v2.api.dmzj.com/old/comment/";

    //登录
    String loginUrl = "http://user.dmzj.com/login/m_confirm";
    //退出
    String exitUrl = "http://v2.api.dmzj.com/device/cancel";

    int[] textSizes = {12,15, 17, 19,21, 23, 25};
}
