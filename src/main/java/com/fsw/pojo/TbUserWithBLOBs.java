package com.fsw.pojo;

public class TbUserWithBLOBs extends TbUser {
    private String collection;

    private String interesting;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection == null ? null : collection.trim();
    }

    public String getInteresting() {
        return interesting;
    }

    public void setInteresting(String interesting) {
        this.interesting = interesting == null ? null : interesting.trim();
    }
}