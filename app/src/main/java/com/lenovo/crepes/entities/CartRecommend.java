package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/17.
 */
public class CartRecommend {

    /**
     * category_id : 46
     * title : 大图推荐
     * sort : 1
     * data : [{"cover":"http://images.dmzj.com/tuijian/750_480/1112guishuizhuanqutj4.jpg","title":"诡水疑云动画第五集上线！","sub_title":"诡水疑云动画第五集上线！","type":6,"url":"http://zt.dmzj.com/guishui_donghua/index_app.html","obj_id":0,"status":""},{"cover":"http://images.dmzj.com/tuijian/750_480/1112kuaibawogetj1.jpg","title":"快把我哥带走·专坑妹妹绝对亲哥","sub_title":"快把我哥带走·专坑妹妹绝对亲哥","type":1,"url":"","obj_id":22897,"status":"连载中"},{"cover":"http://images.dmzj.com/tuijian/750_480/1112xiaofangduitj2.jpg","title":"炎炎之消防队·一紧张就会露出恶魔的笑容","sub_title":"炎炎之消防队·一紧张就会露出恶魔的笑容","type":1,"url":"","obj_id":21605,"status":"连载中"},{"cover":"http://images.dmzj.com/tuijian/750_480/1113droptj1.jpg","title":"Drop Frame·找到自己留下的线索！","sub_title":"Drop Frame·找到自己留下的线索！","type":1,"url":"","obj_id":18880,"status":"连载中"},{"cover":"http://images.dmzj.com/tuijian/750_480/1111guanggunjie9.jpg","title":"话题：光棍节如何用一句话伤害单身狗？","sub_title":"话题：光棍节如何用一句话伤害单身狗？","type":7,"url":"http://v2.api.dmzj.com/article/show/2895.html","obj_id":2895,"status":""}]
     */

    private int category_id;
    private String title;
    private int sort;
    /**
     * cover : http://images.dmzj.com/tuijian/750_480/1112guishuizhuanqutj4.jpg
     * title : 诡水疑云动画第五集上线！
     * sub_title : 诡水疑云动画第五集上线！
     * type : 6
     * url : http://zt.dmzj.com/guishui_donghua/index_app.html
     * obj_id : 0
     * status :
     */

    private List<DataEntity> data;

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getTitle() {
        return title;
    }

    public int getSort() {
        return sort;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String cover;
        private String title;
        private String sub_title;
        private int type;
        private String url;
        private int obj_id;
        private String status;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setObj_id(int obj_id) {
            this.obj_id = obj_id;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCover() {
            return cover;
        }

        public String getTitle() {
            return title;
        }

        public String getSub_title() {
            return sub_title;
        }

        public int getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public int getObj_id() {
            return obj_id;
        }

        public String getStatus() {
            return status;
        }
    }
}
