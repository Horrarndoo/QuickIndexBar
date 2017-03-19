package com.horrarndoo.zyw.quickindexbar.domain;

import com.horrarndoo.zyw.quickindexbar.utils.PinYinUtil;

/**
 * Created by Horrarndoo on 2017/3/19.
 */

public class Person implements Comparable<Person>{
    private String name;
    private String pinyin;

    public Person(String name){
        super();
        this.name = name;
        setPinyin(PinYinUtil.getPinyin(name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(Person another) {
        return getPinyin().compareTo(another.getPinyin());
    }
}
