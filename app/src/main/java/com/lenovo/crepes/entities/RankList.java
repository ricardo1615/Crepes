package com.lenovo.crepes.entities;

/**
 * Created by Administrator on 2016/3/21.
 */
public class RankList {

    /**
     * comic_id : 9949
     * title : 一拳超人
     * authors : 村田雄介/ONE
     * status : 连载中
     * cover : http://images.dmzj.com/webpic/1/yijinan20130105.jpg
     * types : 冒险/欢乐向/格斗
     * last_updatetime : 1457775008
     * num : 6214
     */

    private int comic_id;
    private String title;
    private String authors;
    private String status;
    private String cover;
    private String types;
    private int last_updatetime;
    private int num;

    public void setComic_id(int comic_id) {
        this.comic_id = comic_id;
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

    public void setTypes(String types) {
        this.types = types;
    }

    public void setLast_updatetime(int last_updatetime) {
        this.last_updatetime = last_updatetime;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getComic_id() {
        return comic_id;
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

    public String getTypes() {
        return types;
    }

    public int getLast_updatetime() {
        return last_updatetime;
    }

    public int getNum() {
        return num;
    }
}
