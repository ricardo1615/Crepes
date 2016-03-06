package com.lenovo.crepes.entities;

/**
 * Created by Lenovo on 2015/11/17.
 */
public class FastNews {

    /**
     * id : 398
     * uid : 100355130
     * nickname : SOX教徒
     * content : 时间未知曲目透露，μ’s亚洲巡回上海演唱会获得演出许可！在11月9日上海白玉兰文化艺术发展有限公司向上海市文化局提交了关于『μ’s亚洲巡回上海演唱会的演出申请』，在收件后的一周，上海市文化局正式下发了『μ’s亚洲巡回上海演唱会』的演出行政许可。
     * updatetime : 1447752467
     * img : http://images.dmzj.com/news/letter/130/14477524415568.jpg
     * cover : http://images.dmzj.com/user/80/96/8096f63f8b65ee8d9543a78b5afe9fca.png
     * vote_amount : 0
     */

    private int id;
    private int uid;
    private String nickname;
    private String content;
    private int updatetime;
    private String img;
    private String cover;
    private int vote_amount;

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdatetime(int updatetime) {
        this.updatetime = updatetime;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setVote_amount(int vote_amount) {
        this.vote_amount = vote_amount;
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

    public String getContent() {
        return content;
    }

    public int getUpdatetime() {
        return updatetime;
    }

    public String getImg() {
        return img;
    }

    public String getCover() {
        return cover;
    }

    public int getVote_amount() {
        return vote_amount;
    }
}
