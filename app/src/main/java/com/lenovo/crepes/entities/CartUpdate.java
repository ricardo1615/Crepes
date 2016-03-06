package com.lenovo.crepes.entities;

/**
 * Created by Lenovo on 2015/11/17.
 */
public class CartUpdate {

    /**
     * id : 25295
     * title : 口袋妖怪卡牌
     * islong : 2
     * authors : 松岛リュウ
     * types : 冒险
     * cover : http://images.dmzj.com/webpic/16/1koudaiyaoguaikapai1117.jpg
     * status : 连载中
     * last_update_chapter_name : 大宇怪BREAK
     * last_update_chapter_id : 48018
     * last_updatetime : 1447761107
     */

    private int id;
    private String title;
    private int islong;
    private String authors;
    private String types;
    private String cover;
    private String status;
    private String last_update_chapter_name;
    private int last_update_chapter_id;
    private int last_updatetime;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIslong(int islong) {
        this.islong = islong;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLast_update_chapter_name(String last_update_chapter_name) {
        this.last_update_chapter_name = last_update_chapter_name;
    }

    public void setLast_update_chapter_id(int last_update_chapter_id) {
        this.last_update_chapter_id = last_update_chapter_id;
    }

    public void setLast_updatetime(int last_updatetime) {
        this.last_updatetime = last_updatetime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getIslong() {
        return islong;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTypes() {
        return types;
    }

    public String getCover() {
        return cover;
    }

    public String getStatus() {
        return status;
    }

    public String getLast_update_chapter_name() {
        return last_update_chapter_name;
    }

    public int getLast_update_chapter_id() {
        return last_update_chapter_id;
    }

    public int getLast_updatetime() {
        return last_updatetime;
    }
}
