package com.zmj.demo.domain;

/**
 * http请求返回的最外层对象
 */
public class Result<T> {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体的内容  */
    private T data;

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "code" + getCode() + ",data: " + data.toString() + ", msg: " + getMsg();
    }
}
