package com.flock.event.enums;

/**
 * 事件类型
 * Created by Chunming_Wang on 2019/12/20.
 */
public enum EventType {

    ADD(1, "新增"),
    MODIFY(2, "更新"),
    DROP(3, "删除"),
    UNDEFINE(-1, "未知");

    private int code;
    private String msg;

    EventType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String msg(int code) {
        for (EventType m : EventType.values()) {
            if (m.getCode() == code) {
                return m.getMsg();
            }
        }
        return UNDEFINE.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
