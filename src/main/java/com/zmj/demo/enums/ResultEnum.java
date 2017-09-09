package com.zmj.demo.enums;

public enum ResultEnum {
    UNKOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    PRIMARY_INPUT_NAME(101, "您输入什么名字啊"),
    PRIMARY_INPUT_GENDER(100, "您的性别输入有误，只能是\"male\"或者\"female\"");

    ;
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
