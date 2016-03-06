package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/17.
 */
public class TellMeWhy {

    /**
     * code : 0
     * msg :
     * data : {"category_id":50,"title":"猜你喜欢","sort":5,"data":[{"id":17049,"title":"一条狗","authors":"使徒子","status":"已完结","cover":"http://images.dmzj.com/webpic/11/0210yitiaogoufml.jpg","num":20082633},{"id":8532,"title":"超能力者齐木楠雄的灾难","authors":"麻生周一","status":"连载中","cover":"http://images.dmzj.com/webpic/11/chaonenglizheqimulanxiong.jpg","num":8741195},{"id":12776,"title":"飙速宅男","authors":"渡边航","status":"连载中","cover":"http://images.dmzj.com/webpic/0/biaosuzhainan.jpg","num":7433592}]}
     */

    private int code;
    private String msg;
    /**
     * category_id : 50
     * title : 猜你喜欢
     * sort : 5
     * data : [{"id":17049,"title":"一条狗","authors":"使徒子","status":"已完结","cover":"http://images.dmzj.com/webpic/11/0210yitiaogoufml.jpg","num":20082633},{"id":8532,"title":"超能力者齐木楠雄的灾难","authors":"麻生周一","status":"连载中","cover":"http://images.dmzj.com/webpic/11/chaonenglizheqimulanxiong.jpg","num":8741195},{"id":12776,"title":"飙速宅男","authors":"渡边航","status":"连载中","cover":"http://images.dmzj.com/webpic/0/biaosuzhainan.jpg","num":7433592}]
     */

    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private int category_id;
        private String title;
        private int sort;
        /**
         * id : 17049
         * title : 一条狗
         * authors : 使徒子
         * status : 已完结
         * cover : http://images.dmzj.com/webpic/11/0210yitiaogoufml.jpg
         * num : 20082633
         */

        private List<DataEntity2> data;

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public void setData(List<DataEntity2> data) {
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

        public List<DataEntity2> getData() {
            return data;
        }

        public static class DataEntity2 {
            private int id;
            private String title;
            private String authors;
            private String status;
            private String cover;
            private int num;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setAuthors(String authors) {
                this.authors = authors;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getAuthors() {
                return authors;
            }

            public String getStatus() {
                return status;
            }

            public String getCover() {
                return cover;
            }

            public int getNum() {
                return num;
            }
        }
    }
}
