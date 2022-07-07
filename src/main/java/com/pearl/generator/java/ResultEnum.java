package com.pearl.generator.java;

/**
 * @author TangDan
 * @version 1.0
 * @since 2022/6/29
 */

public enum ResultEnum {

    YF_0000(1000, "成功"),
    YF_9999(9999,"系统异常");


    private Integer code;
    private String msg;

    ResultEnum(Integer code , String msg){
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
