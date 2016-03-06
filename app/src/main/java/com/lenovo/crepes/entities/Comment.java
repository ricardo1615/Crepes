package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/18.
 */
public class Comment {

    /**
     * author :
     * author_id : 0
     * avatar_url : http://images.dmzj.com/user/1c/15/1c15238f4ea1e16f157582b13419c374.png
     * content : 三爷威武
     * createtime : 1446791466
     * hot_comment_amount : 1
     * id : 352817
     * isused : 1
     * nickname : 闷油瓶40104
     * obj_id : 2847
     * pid : 0
     * reply : {"data":[{"author":"闷油瓶40104","author_id":100346136,"avatar_url":"http://images.dmzj.com/user/41/ab/41ab06daf5d9ed0f869df7daeb5ced74.png","content":"回复 @闷油瓶40104:最没内涵的一个","createtime":1446796848,"id":352819,"isused":1,"nickname":"正如3k大神所言","obj_id":2847,"pid":352817,"uid":13879792,"up":2}],"total":1}
     * uid : 100346136
     * up : 0
     */

    private String author;
    private int author_id;
    private String avatar_url;
    private String content;
    private int createtime;
    private int hot_comment_amount;
    private int id;
    private int isused;
    private String nickname;
    private int obj_id;
    private int pid;
    /**
     * data : [{"author":"闷油瓶40104","author_id":100346136,"avatar_url":"http://images.dmzj.com/user/41/ab/41ab06daf5d9ed0f869df7daeb5ced74.png","content":"回复 @闷油瓶40104:最没内涵的一个","createtime":1446796848,"id":352819,"isused":1,"nickname":"正如3k大神所言","obj_id":2847,"pid":352817,"uid":13879792,"up":2}]
     * total : 1
     */

    private ReplyEntity reply;
    private int uid;
    private int up;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public void setHot_comment_amount(int hot_comment_amount) {
        this.hot_comment_amount = hot_comment_amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsused(int isused) {
        this.isused = isused;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setObj_id(int obj_id) {
        this.obj_id = obj_id;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setReply(ReplyEntity reply) {
        this.reply = reply;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public String getAuthor() {
        return author;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getContent() {
        return content;
    }

    public int getCreatetime() {
        return createtime;
    }

    public int getHot_comment_amount() {
        return hot_comment_amount;
    }

    public int getId() {
        return id;
    }

    public int getIsused() {
        return isused;
    }

    public String getNickname() {
        return nickname;
    }

    public int getObj_id() {
        return obj_id;
    }

    public int getPid() {
        return pid;
    }

    public ReplyEntity getReply() {
        return reply;
    }

    public int getUid() {
        return uid;
    }

    public int getUp() {
        return up;
    }

    public static class ReplyEntity {
        private int total;
        /**
         * author : 闷油瓶40104
         * author_id : 100346136
         * avatar_url : http://images.dmzj.com/user/41/ab/41ab06daf5d9ed0f869df7daeb5ced74.png
         * content : 回复 @闷油瓶40104:最没内涵的一个
         * createtime : 1446796848
         * id : 352819
         * isused : 1
         * nickname : 正如3k大神所言
         * obj_id : 2847
         * pid : 352817
         * uid : 13879792
         * up : 2
         */

        private List<DataEntity> data;

        public void setTotal(int total) {
            this.total = total;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public int getTotal() {
            return total;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public static class DataEntity {
            private String author;
            private int author_id;
            private String avatar_url;
            private String content;
            private int createtime;
            private int id;
            private int isused;
            private String nickname;
            private int obj_id;
            private int pid;
            private int uid;
            private int up;

            public void setAuthor(String author) {
                this.author = author;
            }

            public void setAuthor_id(int author_id) {
                this.author_id = author_id;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setIsused(int isused) {
                this.isused = isused;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setObj_id(int obj_id) {
                this.obj_id = obj_id;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public void setUp(int up) {
                this.up = up;
            }

            public String getAuthor() {
                return author;
            }

            public int getAuthor_id() {
                return author_id;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public String getContent() {
                return content;
            }

            public int getCreatetime() {
                return createtime;
            }

            public int getId() {
                return id;
            }

            public int getIsused() {
                return isused;
            }

            public String getNickname() {
                return nickname;
            }

            public int getObj_id() {
                return obj_id;
            }

            public int getPid() {
                return pid;
            }

            public int getUid() {
                return uid;
            }

            public int getUp() {
                return up;
            }
        }
    }
}
