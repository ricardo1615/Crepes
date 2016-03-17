package com.lenovo.crepes.entities;

/**
 * Created by Administrator on 2016/3/17.
 */
public class SendCommentMeg {

    /**
     * result : 1
     * msg : 发表成功
     * data : {"id":36767,"uid":100528939,"nickname":"屠圣之枪","avatar_url":"http://images.dmzj.com/user/17/72/177206e0f00fae915f348b6ec6877826.png","pid":36767,"obj_id":4557,"author_id":"","author":"","content":"不知道什么时候vr技术成熟","createtime":1458194403}
     */

    private int result;
    private String msg;
    /**
     * id : 36767
     * uid : 100528939
     * nickname : 屠圣之枪
     * avatar_url : http://images.dmzj.com/user/17/72/177206e0f00fae915f348b6ec6877826.png
     * pid : 36767
     * obj_id : 4557
     * author_id :
     * author :
     * content : 不知道什么时候vr技术成熟
     * createtime : 1458194403
     */

    private DataEntity data;

    public void setResult(int result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private int uid;
        private String nickname;
        private String avatar_url;
        private int pid;
        private int obj_id;
        private String author_id;
        private String author;
        private String content;
        private int createtime;

        public void setId(int id) {
            this.id = id;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public void setObj_id(int obj_id) {
            this.obj_id = obj_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public int getUid() {
            return uid;
        }

        public String getNickname() {
            return nickname;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public int getPid() {
            return pid;
        }

        public int getObj_id() {
            return obj_id;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        public int getCreatetime() {
            return createtime;
        }
    }
}
