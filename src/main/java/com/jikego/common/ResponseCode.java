package com.jikego.common;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/6/22 9:43
 * @Description: 响应编码枚举类
 */
public enum ResponseCode {
    /**
     * 响应编码枚举类
     */
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

