package com.lenovo.crepes.entities;

/**
 * Created by Lenovo on 2015/11/18.
 */
public class LoginResult {

    /**
     * result : 1
     * msg : OK
     * data : {"uid":"100528939","nickname":"屠圣之枪","photo":"http://images.dmzj.com/user/17/72/177206e0f00fae915f348b6ec6877826.png"}
     */

    private int result;
    private String msg;
    /**
     * uid : 100528939
     * nickname : 屠圣之枪
     * photo : http://images.dmzj.com/user/17/72/177206e0f00fae915f348b6ec6877826.png
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
        private String uid;
        private String nickname;
        private String photo;
        private int textSizeIndex = 2;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getUid() {
            return uid;
        }

        public String getNickname() {
            return nickname;
        }

        public String getPhoto() {
            return photo;
        }

        public int getTextSizeIndex() {
            return textSizeIndex;
        }

        public void setTextSizeIndex(int textSizeIndex) {
            this.textSizeIndex = textSizeIndex;
        }
    }
}
