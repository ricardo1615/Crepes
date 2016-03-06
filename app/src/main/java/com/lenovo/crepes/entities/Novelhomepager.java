package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Berserker on 2015/11/17.
 */
public class Novelhomepager {

    /**
     * category_id : 57
     * sort : 0
     * title : 轮番图
     * data : [{"id":1359,"obj_id":2012,"title":"我是蜘蛛，怎么了？","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/06.jpg","url":"","type":2,"sub_title":"","status":"连载中"},{"id":1336,"obj_id":1602,"title":"无职转生~在异世界认真地活下去~","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/03.jpg","url":"","type":2,"sub_title":"","status":"连载中"},{"id":1337,"obj_id":1607,"title":"OVERLORD不死者之王","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/04.jpg","url":"","type":2,"sub_title":"","status":"连载中"},{"id":1338,"obj_id":1571,"title":"对魔导学园35试验小队","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/02.jpg","url":"","type":2,"sub_title":"","status":"连载中"},{"id":1339,"obj_id":1291,"title":"我被绑架到贵族女校当\u201c庶民样本\u201d","cover":"http://images.dmzj.com/tuijian/xiaoshuo/750-480/05.jpg","url":"","type":2,"sub_title":"","status":"连载中"}]
     */

    private int category_id;
    private int sort;
    private String title;
    /**
     * id : 1359
     * obj_id : 2012
     * title : 我是蜘蛛，怎么了？
     * cover : http://images.dmzj.com/tuijian/xiaoshuo/750-480/06.jpg
     * url :
     * type : 2
     * sub_title :
     * status : 连载中
     */

    private List<DataEntity> data;

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCategory_id() {
        return category_id;
    }

    public int getSort() {
        return sort;
    }

    public String getTitle() {
        return title;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private int obj_id;
        private String title;
        private String cover;
        private String url;
        private int type;
        private String sub_title;
        private String status;

        public void setId(int id) {
            this.id = id;
        }

        public void setObj_id(int obj_id) {
            this.obj_id = obj_id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public int getObj_id() {
            return obj_id;
        }

        public String getTitle() {
            return title;
        }

        public String getCover() {
            return cover;
        }

        public String getUrl() {
            return url;
        }

        public int getType() {
            return type;
        }

        public String getSub_title() {
            return sub_title;
        }

        public String getStatus() {
            return status;
        }
    }
}
