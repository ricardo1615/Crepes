package com.lenovo.crepes.entities;

/**
 * Created by Lenovo on 2015/11/18.
 */
public class CommentAmount {

    /**
     * code : 0
     * msg : ok
     * amount : 108
     */

    private int code;
    private String msg;
    private int amount;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getAmount() {
        return amount;
    }
}
