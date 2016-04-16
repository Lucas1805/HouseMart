package com.example.models;

/**
 * Created by Megurine Lucas on 16-04-2016.
 */
public class Province {
    private String id;
    private String provinceName;

    public Province(String id, String provinceName) {
        this.id = id;
        this.provinceName = provinceName;
    }

    public Province() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
