package com.heaven.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class R {

    private int code;
    private String message;
    private Map<String,Object> data = new HashMap<>();

    private R(){}

//    成功
    public static R ok(){
        R r = new R();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

//    失败
    public static R error(){
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

//    设置特定的结果
    public static R setResult(ResponseEnum responseEnum){
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    public R data(String s,Object o){
        this.data.put(s,o);
        return this;
    }

    public R message(String s){
        this.setMessage(s);
        return this;
    }

    public R code(Integer s){
        this.code=s;
        return this;
    }

    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }


}
