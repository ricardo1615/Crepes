package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/17.
 */
public class HeadNews {

    /**
     * code : 0
     * data : [{"id":188,"object_id":2841,"object_url":"http://v2.api.dmzj.com/article/show/2841.html","pic_url":"http://images.dmzj.com/news/recommend/14472997505497.jpg","title":"万人票选2015年10月新番追番榜"}]
     * msg : 成功
     */

    private int code;
    private String msg;
    /**
     * id : 188
     * object_id : 2841
     * object_url : http://v2.api.dmzj.com/article/show/2841.html
     * pic_url : http://images.dmzj.com/news/recommend/14472997505497.jpg
     * title : 万人票选2015年10月新番追番榜
     */

    private List<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private int object_id;
        private String object_url;
        private String pic_url;
        private String title;

        public void setId(int id) {
            this.id = id;
        }

        public void setObject_id(int object_id) {
            this.object_id = object_id;
        }

        public void setObject_url(String object_url) {
            this.object_url = object_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public int getObject_id() {
            return object_id;
        }

        public String getObject_url() {
            return object_url;
        }

        public String getPic_url() {
            return pic_url;
        }

        public String getTitle() {
            return title;
        }
    }
}
