package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/21.
 */
public class ChapterMeg {


    /**
     * id : 43668
     * comic_id : 8259
     * chapter_name : 第181话
     * chapter_order : 1800
     * page : ["http://imgsmall.dmzj.com/d/8259/43668/0.jpg","http://imgsmall.dmzj.com/d/8259/43668/1.jpg","http://imgsmall.dmzj.com/d/8259/43668/2.jpg","http://imgsmall.dmzj.com/d/8259/43668/3.jpg","http://imgsmall.dmzj.com/d/8259/43668/4.jpg","http://imgsmall.dmzj.com/d/8259/43668/5.jpg","http://imgsmall.dmzj.com/d/8259/43668/6.jpg","http://imgsmall.dmzj.com/d/8259/43668/7.jpg","http://imgsmall.dmzj.com/d/8259/43668/8.jpg","http://imgsmall.dmzj.com/d/8259/43668/9.jpg","http://imgsmall.dmzj.com/d/8259/43668/10.jpg","http://imgsmall.dmzj.com/d/8259/43668/11.jpg","http://imgsmall.dmzj.com/d/8259/43668/12.jpg","http://imgsmall.dmzj.com/d/8259/43668/13.jpg","http://imgsmall.dmzj.com/d/8259/43668/14.jpg","http://imgsmall.dmzj.com/d/8259/43668/15.jpg","http://imgsmall.dmzj.com/d/8259/43668/16.jpg","http://imgsmall.dmzj.com/d/8259/43668/17.jpg","http://imgsmall.dmzj.com/d/8259/43668/18.jpg","http://imgsmall.dmzj.com/d/8259/43668/19.jpg"]
     * size : [{"width":900,"height":1305},{"width":900,"height":1301},{"width":900,"height":1298},{"width":900,"height":1301},{"width":900,"height":1296},{"width":900,"height":1302},{"width":900,"height":1296},{"width":900,"height":1306},{"width":900,"height":1293},{"width":900,"height":1301},{"width":900,"height":1296},{"width":900,"height":1293},{"width":900,"height":1299},{"width":900,"height":1304},{"width":900,"height":1295},{"width":900,"height":1298},{"width":900,"height":1292},{"width":900,"height":1310},{"width":900,"height":1297},{"width":900,"height":1308}]
     * islong : 2
     */

    private String id;
    private String comic_id;
    private String chapter_name;
    private String chapter_order;
    private String islong;
    private List<String> page;
    /**
     * width : 900
     * height : 1305
     */

    private List<SizeEntity> size;

    public void setId(String id) {
        this.id = id;
    }

    public void setComic_id(String comic_id) {
        this.comic_id = comic_id;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public void setChapter_order(String chapter_order) {
        this.chapter_order = chapter_order;
    }

    public void setIslong(String islong) {
        this.islong = islong;
    }

    public void setPage(List<String> page) {
        this.page = page;
    }

    public void setSize(List<SizeEntity> size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public String getComic_id() {
        return comic_id;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public String getChapter_order() {
        return chapter_order;
    }

    public String getIslong() {
        return islong;
    }

    public List<String> getPage() {
        return page;
    }

    public List<SizeEntity> getSize() {
        return size;
    }

    public static class SizeEntity {
        private int width;
        private int height;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
