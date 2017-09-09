package com.zmj.demo.utils;

import com.zmj.demo.domain.Result;

public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(200);
        result.setMsg(null);
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
