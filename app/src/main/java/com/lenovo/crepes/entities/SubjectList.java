package com.lenovo.crepes.entities;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/26.
 */
public class SubjectList implements Serializable{

    /**
     * id : 68
     * title : 新漫周刊第10期 一周新漫推荐
     * short_title : 新漫周刊第十期
     * create_time : 1458702525
     * small_cover : http://images.dmzj.com/subject/68/small_cover_1458790924.jpg
     * page_type : 3
     * sort : 680
     * page_url : xinmanzhoukan10
     */

    private int id;
    private String title;
    private String short_title;
    private int create_time;
    private String small_cover;
    private int page_type;
    private int sort;
    private String page_url;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public void setSmall_cover(String small_cover) {
        this.small_cover = small_cover;
    }

    public void setPage_type(int page_type) {
        this.page_type = page_type;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setPage_url(String page_url) {
        this.page_url = page_url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShort_title() {
        return short_title;
    }

    public int getCreate_time() {
        return create_time;
    }

    public String getSmall_cover() {
        return small_cover;
    }

    public int getPage_type() {
        return page_type;
    }

    public int getSort() {
        return sort;
    }

    public String getPage_url() {
        return page_url;
    }
}
