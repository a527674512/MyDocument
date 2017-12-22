package com.example.lhf.mydocument.bean;

/**
 * Created by lenovo on 2017/11/16.
 */

public class Fruit {

    private String name;

    private int imageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    public Fruit(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }
}
