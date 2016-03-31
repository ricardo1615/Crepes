package com.lenovo.crepes.entities;

/**
 * Created by Administrator on 2016/3/31.
 */
public class SubscribeComic {

    /**
     * name : 双星之阴阳师
     * sub_update : 第05卷
     * sub_img : http://images.dmzj.com/webpic/8/160131shuangxing2fml.jpg
     * sub_uptime : 1459416028
     * sub_first_letter : s
     * sub_readed : 1
     * id : 13318
     * status : 连载中
     */

    private String name;
    private String sub_update;
    private String sub_img;
    private int sub_uptime;
    private String sub_first_letter;
    private int sub_readed;
    private int id;
    private String status;

    public void setName(String name) {
        this.name = name;
    }

    public void setSub_update(String sub_update) {
        this.sub_update = sub_update;
    }

    public void setSub_img(String sub_img) {
        this.sub_img = sub_img;
    }

    public void setSub_uptime(int sub_uptime) {
        this.sub_uptime = sub_uptime;
    }

    public void setSub_first_letter(String sub_first_letter) {
        this.sub_first_letter = sub_first_letter;
    }

    public void setSub_readed(int sub_readed) {
        this.sub_readed = sub_readed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getSub_update() {
        return sub_update;
    }

    public String getSub_img() {
        return sub_img;
    }

    public int getSub_uptime() {
        return sub_uptime;
    }

    public String getSub_first_letter() {
        return sub_first_letter;
    }

    public int getSub_readed() {
        return sub_readed;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
