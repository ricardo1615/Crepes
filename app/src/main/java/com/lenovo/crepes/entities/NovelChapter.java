package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/20.
 */
public class NovelChapter {

    /**
     * chapters : [{"chapter_id":30396,"chapter_name":"登场人物","chapter_order":10},{"chapter_id":30397,"chapter_name":"序章 欢迎你庶民","chapter_order":20},{"chapter_id":30398,"chapter_name":"第1话 不是我吗","chapter_order":30},{"chapter_id":30399,"chapter_name":"第2话 气味不错","chapter_order":40},{"chapter_id":30400,"chapter_name":"第3话 最喜欢肌肉了！","chapter_order":50},{"chapter_id":30401,"chapter_name":"第4话 我来写感谢信","chapter_order":60},{"chapter_id":30402,"chapter_name":"第5话 好像有五成左右几率可以成功的哦","chapter_order":70},{"chapter_id":30403,"chapter_name":"第6话 是说我被肌肉男紧紧抱住的时候，手机里珍藏的图片都换成肌肉精华图了吗","chapter_order":80},{"chapter_id":30404,"chapter_name":"第7话 请教教我庶民的事情！！","chapter_order":90},{"chapter_id":30405,"chapter_name":"第8话 纯傲（纯洁高傲）小姐是谁！？","chapter_order":100},{"chapter_id":30406,"chapter_name":"第9话 丽子大人可是我们心驰神往的存在啊","chapter_order":110},{"chapter_id":30407,"chapter_name":"第10话 您做过电车吗？","chapter_order":120},{"chapter_id":30408,"chapter_name":"第11话 我是觉得挺大挺大的，可居然这么大","chapter_order":130},{"chapter_id":30409,"chapter_name":"第12话 你只是在吃饭吧（爱佳小姐的日常）","chapter_order":140},{"chapter_id":30410,"chapter_name":"第13话 那么的话我也要成为《能力者》！","chapter_order":150},{"chapter_id":30411,"chapter_name":"第14话 你要把哪儿作为文化遗产！？","chapter_order":160},{"chapter_id":30412,"chapter_name":"第15话 那个，不穿胖次的白亚小姐！？（汐留白亚登场）","chapter_order":170},{"chapter_id":30413,"chapter_name":"第16话 感觉就像是伊甸园（神领可怜登场）","chapter_order":180},{"chapter_id":30414,"chapter_name":"第17话 \u201c会话训练\u201d哦（爱佳小姐的日常\u2014\u20142）","chapter_order":190},{"chapter_id":30415,"chapter_name":"第18话 这家伙容易孤独啊","chapter_order":200},{"chapter_id":30416,"chapter_name":"第19话 请不要在意","chapter_order":210},{"chapter_id":30529,"chapter_name":"第20话 这儿就交给庶民吧","chapter_order":220},{"chapter_id":30530,"chapter_name":"第21话 笨\u2014\u2014蛋","chapter_order":230},{"chapter_id":30531,"chapter_name":"第22话 庶民在做着这么棒的事情呢！","chapter_order":240},{"chapter_id":30532,"chapter_name":"第23话 讨厌","chapter_order":250},{"chapter_id":30539,"chapter_name":"终章","chapter_order":260},{"chapter_id":30540,"chapter_name":"爱佳的梦","chapter_order":270},{"chapter_id":30541,"chapter_name":"后记","chapter_order":280}]
     * volume_id : 4693
     * volume_name : 第一卷
     * volume_order : 10
     */

    private int volume_id;
    private String volume_name;
    private int volume_order;
    /**
     * chapter_id : 30396
     * chapter_name : 登场人物
     * chapter_order : 10
     */

    private List<ChaptersEntity> chapters;

    public void setVolume_id(int volume_id) {
        this.volume_id = volume_id;
    }

    public void setVolume_name(String volume_name) {
        this.volume_name = volume_name;
    }

    public void setVolume_order(int volume_order) {
        this.volume_order = volume_order;
    }

    public void setChapters(List<ChaptersEntity> chapters) {
        this.chapters = chapters;
    }

    public int getVolume_id() {
        return volume_id;
    }

    public String getVolume_name() {
        return volume_name;
    }

    public int getVolume_order() {
        return volume_order;
    }

    public List<ChaptersEntity> getChapters() {
        return chapters;
    }

    public static class ChaptersEntity {
        private int p_volume_id;
        private String p_volume_name;
        private int p_volume_order;
        private int chapter_id;
        private String chapter_name;
        private int chapter_order;

        public void setChapter_id(int chapter_id) {
            this.chapter_id = chapter_id;
        }

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }

        public void setChapter_order(int chapter_order) {
            this.chapter_order = chapter_order;
        }

        public int getChapter_id() {
            return chapter_id;
        }

        public String getChapter_name() {
            return chapter_name;
        }

        public int getChapter_order() {
            return chapter_order;
        }

        public int getP_volume_id() {
            return p_volume_id;
        }

        public void setP_volume_id(int p_volume_id) {
            this.p_volume_id = p_volume_id;
        }

        public String getP_volume_name() {
            return p_volume_name;
        }

        public void setP_volume_name(String p_volume_name) {
            this.p_volume_name = p_volume_name;
        }

        public int getP_volume_order() {
            return p_volume_order;
        }

        public void setP_volume_order(int p_volume_order) {
            this.p_volume_order = p_volume_order;
        }
    }
}
