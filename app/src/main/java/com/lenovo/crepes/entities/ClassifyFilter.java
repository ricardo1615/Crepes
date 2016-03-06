package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/18.
 */
public class ClassifyFilter {

    /**
     * title : 题材
     * items : [{"tag_id":0,"tag_name":"全部"},{"tag_id":4,"tag_name":"冒险"},{"tag_id":3243,"tag_name":"百合"},{"tag_id":3242,"tag_name":"生活"},{"tag_id":17,"tag_name":"四格"},{"tag_id":3244,"tag_name":"伪娘"},{"tag_id":3245,"tag_name":"悬疑"},{"tag_id":3250,"tag_name":"历史"},{"tag_id":3249,"tag_name":"后宫"},{"tag_id":3248,"tag_name":"热血"},{"tag_id":3246,"tag_name":"耽美"},{"tag_id":16,"tag_name":"其他"},{"tag_id":14,"tag_name":"恐怖"},{"tag_id":7,"tag_name":"科幻"},{"tag_id":6,"tag_name":"格斗"},{"tag_id":5,"tag_name":"欢乐向"},{"tag_id":8,"tag_name":"爱情"},{"tag_id":9,"tag_name":"侦探"},{"tag_id":13,"tag_name":"校园"},{"tag_id":12,"tag_name":"神鬼"},{"tag_id":11,"tag_name":"魔法"},{"tag_id":10,"tag_name":"竞技"},{"tag_id":3251,"tag_name":"战争"},{"tag_id":3252,"tag_name":"萌系"},{"tag_id":5806,"tag_name":"魔幻"},{"tag_id":5345,"tag_name":"扶她"},{"tag_id":5077,"tag_name":"东方"},{"tag_id":5848,"tag_name":"奇幻"},{"tag_id":6219,"tag_name":"节操"},{"tag_id":7900,"tag_name":"仙侠"},{"tag_id":7568,"tag_name":"搞笑"},{"tag_id":6437,"tag_name":"颜艺"},{"tag_id":6316,"tag_name":"轻小说"},{"tag_id":4518,"tag_name":"性转换"},{"tag_id":4459,"tag_name":"高清单行"},{"tag_id":3255,"tag_name":"励志"},{"tag_id":3254,"tag_name":"治愈"},{"tag_id":3253,"tag_name":"宅系"},{"tag_id":3324,"tag_name":"武侠"},{"tag_id":3325,"tag_name":"机战"},{"tag_id":3365,"tag_name":"西方魔幻"},{"tag_id":3328,"tag_name":"职场"},{"tag_id":3327,"tag_name":"美食"},{"tag_id":3326,"tag_name":"音乐舞蹈"}]
     */

    private String title;
    /**
     * tag_id : 0
     * tag_name : 全部
     */

    private List<ItemsEntity> items;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setItems(List<ItemsEntity> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public List<ItemsEntity> getItems() {
        return items;
    }

    public static class ItemsEntity {
        private int tag_id;
        private String tag_name;

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public int getTag_id() {
            return tag_id;
        }

        public String getTag_name() {
            return tag_name;
        }
    }
}
