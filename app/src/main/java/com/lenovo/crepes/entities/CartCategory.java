package com.lenovo.crepes.entities;

/**
 * Created by Lenovo on 2015/11/18.
 */
public class CartCategory {

    /**
     * tag_id : 2304
     * title : 日本
     * cover : http://images.dmzj.com/tuijian/222_222/ribenmanhua.jpg
     */

    private int tag_id;
    private String title;
    private String cover;

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getTag_id() {
        return tag_id;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }
}
