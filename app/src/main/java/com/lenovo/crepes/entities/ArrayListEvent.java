package com.lenovo.crepes.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lenovo on 2015/11/21.
 */
public class ArrayListEvent implements Serializable {
    private ArrayList<NovelChapter.ChaptersEntity> list;

    public ArrayListEvent(ArrayList<NovelChapter.ChaptersEntity> list) {
        this.list = list;
    }

    public ArrayList<NovelChapter.ChaptersEntity> getList() {
        return list;
    }

    public void setList(ArrayList<NovelChapter.ChaptersEntity> list) {
        this.list = list;
    }
}
