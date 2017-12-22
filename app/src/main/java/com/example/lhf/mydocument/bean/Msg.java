package com.example.lhf.mydocument.bean;

/**
 * Created by lenovo on 2017/11/20.
 */

public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String content;
    private int type;

    public Msg (String content , int type){
        this.content = content;
        this.type = type;
    }

    public String getContent(){
      return content;
    }

    public int getType(){
        return type;
    }
}
