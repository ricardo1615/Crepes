package com.lenovo.crepes.entities;

/**
 * Created by Lenovo on 2015/11/19.
 */
public class CartCategoryResult {

    /**
     * authors : Hun/zhena
     * cover : http://images.dmzj.com/webpic/3/1112kuangyeshaonvfml.jpg
     * id : 12635
     * last_updatetime : 1447849999
     * num : 13989420
     * status : 连载中
     * title : 狂野少女
     * types : 格斗/爱情/校园/热血
     */

    private String authors;
    private String cover;
    private int id;
    private int last_updatetime;
    private int num;
    private String status;
    private String title;
    private String types;

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLast_updatetime(int last_updatetime) {
        this.last_updatetime = last_updatetime;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getAuthors() {
        return authors;
    }

    public String getCover() {
        return cover;
    }

    public int getId() {
        return id;
    }

    public int getLast_updatetime() {
        return last_updatetime;
    }

    public int getNum() {
        return num;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getTypes() {
        return types;
    }
}
