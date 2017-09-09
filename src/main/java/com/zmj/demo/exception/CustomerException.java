package com.zmj.demo.exception;

import com.zmj.demo.enums.ResultEnum;

public class CustomerException extends RuntimeException{
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CustomerException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public CustomerException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}

