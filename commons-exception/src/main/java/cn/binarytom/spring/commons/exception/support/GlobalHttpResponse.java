package cn.binarytom.spring.commons.exception.support;

import com.alibaba.fastjson.JSON;

/**
 * @author lieh
 * @date 2019-05-14 20:35
 **/
public class GlobalHttpResponse {
    private int code;
    private String msg;
    private Object data;

    public GlobalHttpResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
