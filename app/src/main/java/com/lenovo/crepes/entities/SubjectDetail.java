package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Administrator on 2016/3/27.
 */
public class SubjectDetail {

    /**
     * mobile_header_pic : http://images.dmzj.com/subject/66/mobile_header_pic_1457679153.jpg
     * title : 《漫画会》——给你看不一样的好故事 - 漫画会专题
     * description : 《漫画会》给你看不一样的好故事。《漫画会》是由“故事会”出品的16开全彩漫画杂志，创刊于2014年9月。杂志面向青少年读者，以“漫画开启快乐，故事传递梦想”为办刊理念，汇集国内的一线漫画家，推出优秀的中国原创漫画作品，树立有影响力的动漫媒体品牌。
     * comics : [{"cover":"http://images.dmzj.com/img/webpic/11/1009130311456386132.jpg","recommend_brief":"L.Dart","recommend_reason":"红色魔都\u2014\u2014善见城。红色夜空的都市，被一堵难以逾越的城墙所围绕。遍布街道的僧侣混杂在人群中\u2026\u2026","id":29507,"name":"幻夜浮屠","alias_name":""},{"cover":"http://images.dmzj.com/img/webpic/7/1009129471456383958.jpg","recommend_brief":"易飘扬×周烈焚","recommend_reason":"天才大侦探福尔马林，虽是丧尸，但智商一点也不比单细胞生物低！和普通丧尸不同，福尔马林通过\u2026\u2026","id":29513,"name":"大侦探福尔马林","alias_name":""},{"cover":"http://images.dmzj.com/img/webpic/18/1009129781456384920.jpg","recommend_brief":"LING×ICee","recommend_reason":"因为一场车祸，黑雨变成了孤儿，但是事故似有隐情，而他也会常常被噩梦惊醒。看似平凡的生活因为\u2026\u2026","id":29512,"name":"龙刃","alias_name":""},{"cover":"http://images.dmzj.com/img/webpic/9/1009129891456385125.jpg","recommend_brief":"洛君麟×墨妃","recommend_reason":"盛产侠盗之士的神偷门门规规定下山弟子必须做好第一桩单子才可保证今后事业一帆风顺。","id":29511,"name":"盗香语","alias_name":""},{"cover":"http://images.dmzj.com/img/webpic/17/1456390478.jpg","recommend_brief":"杨元超","recommend_reason":"二十二世纪某日，地球遭到了来自外星文明的进攻，在几乎所有城市陷入战火之中的时候，专职负责\u2026\u2026","id":29506,"name":"风之子","alias_name":""},{"cover":"http://images.dmzj.com/img/webpic/18/1456390578.jpg","recommend_brief":"杨小花","recommend_reason":"刘进\u2014\u2014景杉高校的一名气焰嚣张的不良少年，爱面子讲义气，一根筋热血白痴。就是这样的他\u2026\u2026","id":29510,"name":"飓风13号","alias_name":""},{"cover":"http://images.dmzj.com/img/webpic/5/1009130051456385608.jpg","recommend_brief":"猫要喝咖啡","recommend_reason":"本作品是王道少年漫画作品，是描写两个少年实现梦想与约定的成长故事。名为\u201c污染者\u201d的怪物\u2026\u2026","id":29509,"name":"拂晓Daybreak","alias_name":""},{"cover":"http://images.dmzj.com/img/webpic/2/1456390647.jpg","recommend_brief":"墨飞","recommend_reason":"卧龙帮战（cun）士（min）VS外星战士，搞怪爆笑！出人意料！壮烈究极的对决！压倒性的高超画功\u2026\u2026","id":29508,"name":"勇敢的心","alias_name":""}]
     * comment_amount : 5
     */

    private String mobile_header_pic;
    private String title;
    private String description;
    private int comment_amount;
    /**
     * cover : http://images.dmzj.com/img/webpic/11/1009130311456386132.jpg
     * recommend_brief : L.Dart
     * recommend_reason : 红色魔都——善见城。红色夜空的都市，被一堵难以逾越的城墙所围绕。遍布街道的僧侣混杂在人群中……
     * id : 29507
     * name : 幻夜浮屠
     * alias_name :
     */

    private List<ComicsEntity> comics;

    public void setMobile_header_pic(String mobile_header_pic) {
        this.mobile_header_pic = mobile_header_pic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComment_amount(int comment_amount) {
        this.comment_amount = comment_amount;
    }

    public void setComics(List<ComicsEntity> comics) {
        this.comics = comics;
    }

    public String getMobile_header_pic() {
        return mobile_header_pic;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getComment_amount() {
        return comment_amount;
    }

    public List<ComicsEntity> getComics() {
        return comics;
    }

    public static class ComicsEntity {
        private String cover;
        private String recommend_brief;
        private String recommend_reason;
        private int id;
        private String name;
        private String alias_name;

        public void setCover(String cover) {
            this.cover = cover;
        }

        public void setRecommend_brief(String recommend_brief) {
            this.recommend_brief = recommend_brief;
        }

        public void setRecommend_reason(String recommend_reason) {
            this.recommend_reason = recommend_reason;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAlias_name(String alias_name) {
            this.alias_name = alias_name;
        }

        public String getCover() {
            return cover;
        }

        public String getRecommend_brief() {
            return recommend_brief;
        }

        public String getRecommend_reason() {
            return recommend_reason;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAlias_name() {
            return alias_name;
        }
    }
}
