package com.lenovo.crepes.entities;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/20.
 */
public class NovelDetail {

    /**
     * id : 1291
     * name : 关于我被绑架到大小姐学校当庶民样本这件事
     * zone : 日本
     * status : 连载中
     * last_update_volume_name : 第十卷
     * last_update_chapter_name : 后记
     * last_update_volume_id : 7294
     * last_update_chapter_id : 54141
     * last_update_time : 1446555931
     * cover : http://xs.dmzj.com/img/webpic/1/gywblddxjxx.jpg
     * hot_hits : 3322089
     * introduction : “你被选中成为我校的《庶民样本》了”
     凡人一名的高中生神乐坂公人，某天被绑架到了“清华学院女子学校”！
     在这所拥有传统和门第的学校里学习生活的都是名门闺秀，而且她们既没有接触过外面的世界，也没有见过同时代的男性。可以说是超级深闺。为了让她们具有对异性和世间的免疫力。公人被选为“庶民样本”，而对于连手机，游戏和漫画都不知道的大小姐而言，庶民正是让他们向往的存在……！？
     * types : ["校园/爱情"]
     * authors : 七月隆文
     * subscribe_num : 5079
     * volume : [{"id":4693,"lnovel_id":1291,"volume_name":"第一卷","volume_order":10,"addtime":1347704228,"sum_chapters":26},{"id":4731,"lnovel_id":1291,"volume_name":"第二卷","volume_order":20,"addtime":1348715152,"sum_chapters":26},{"id":4782,"lnovel_id":1291,"volume_name":"第三卷","volume_order":30,"addtime":1350738596,"sum_chapters":26},{"id":4867,"lnovel_id":1291,"volume_name":"第四卷","volume_order":40,"addtime":1354077062,"sum_chapters":26},{"id":5099,"lnovel_id":1291,"volume_name":"第五卷","volume_order":50,"addtime":1361862148,"sum_chapters":26},{"id":5296,"lnovel_id":1291,"volume_name":"第六卷","volume_order":60,"addtime":1369739388,"sum_chapters":26},{"id":5557,"lnovel_id":1291,"volume_name":"第七卷","volume_order":70,"addtime":1380520692,"sum_chapters":26},{"id":5832,"lnovel_id":1291,"volume_name":"第7.5卷","volume_order":80,"addtime":1392878741,"sum_chapters":26},{"id":6646,"lnovel_id":1291,"volume_name":"第八卷","volume_order":90,"addtime":1421923507,"sum_chapters":26},{"id":6868,"lnovel_id":1291,"volume_name":"第九卷","volume_order":100,"addtime":1431441183,"sum_chapters":26},{"id":7294,"lnovel_id":1291,"volume_name":"第十卷","volume_order":110,"addtime":1445414625,"sum_chapters":26}]
     */

    private int id;
    private String name;
    private String zone;
    private String status;
    private String last_update_volume_name;
    private String last_update_chapter_name;
    private int last_update_volume_id;
    private int last_update_chapter_id;
    private int last_update_time;
    private String cover;
    private int hot_hits;
    private String introduction;
    private String authors;
    private int subscribe_num;
    private List<String> types;
    /**
     * id : 4693
     * lnovel_id : 1291
     * volume_name : 第一卷
     * volume_order : 10
     * addtime : 1347704228
     * sum_chapters : 26
     */

    private List<VolumeEntity> volume;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLast_update_volume_name(String last_update_volume_name) {
        this.last_update_volume_name = last_update_volume_name;
    }

    public void setLast_update_chapter_name(String last_update_chapter_name) {
        this.last_update_chapter_name = last_update_chapter_name;
    }

    public void setLast_update_volume_id(int last_update_volume_id) {
        this.last_update_volume_id = last_update_volume_id;
    }

    public void setLast_update_chapter_id(int last_update_chapter_id) {
        this.last_update_chapter_id = last_update_chapter_id;
    }

    public void setLast_update_time(int last_update_time) {
        this.last_update_time = last_update_time;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setHot_hits(int hot_hits) {
        this.hot_hits = hot_hits;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setSubscribe_num(int subscribe_num) {
        this.subscribe_num = subscribe_num;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public void setVolume(List<VolumeEntity> volume) {
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getZone() {
        return zone;
    }

    public String getStatus() {
        return status;
    }

    public String getLast_update_volume_name() {
        return last_update_volume_name;
    }

    public String getLast_update_chapter_name() {
        return last_update_chapter_name;
    }

    public int getLast_update_volume_id() {
        return last_update_volume_id;
    }

    public int getLast_update_chapter_id() {
        return last_update_chapter_id;
    }

    public int getLast_update_time() {
        return last_update_time;
    }

    public String getCover() {
        return cover;
    }

    public int getHot_hits() {
        return hot_hits;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getAuthors() {
        return authors;
    }

    public int getSubscribe_num() {
        return subscribe_num;
    }

    public List<String> getTypes() {
        return types;
    }

    public List<VolumeEntity> getVolume() {
        return volume;
    }

    public static class VolumeEntity {
        private int id;
        private int lnovel_id;
        private String volume_name;
        private int volume_order;
        private int addtime;
        private int sum_chapters;

        public void setId(int id) {
            this.id = id;
        }

        public void setLnovel_id(int lnovel_id) {
            this.lnovel_id = lnovel_id;
        }

        public void setVolume_name(String volume_name) {
            this.volume_name = volume_name;
        }

        public void setVolume_order(int volume_order) {
            this.volume_order = volume_order;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public void setSum_chapters(int sum_chapters) {
            this.sum_chapters = sum_chapters;
        }

        public int getId() {
            return id;
        }

        public int getLnovel_id() {
            return lnovel_id;
        }

        public String getVolume_name() {
            return volume_name;
        }

        public int getVolume_order() {
            return volume_order;
        }

        public int getAddtime() {
            return addtime;
        }

        public int getSum_chapters() {
            return sum_chapters;
        }
    }
}
