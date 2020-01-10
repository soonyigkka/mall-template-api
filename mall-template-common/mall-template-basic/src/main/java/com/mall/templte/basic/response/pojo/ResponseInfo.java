package com.mall.templte.basic.response.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回对象封装
 * 
 * @author yangkai3
 *
 * @param <T>
 */
public class ResponseInfo<T> implements Serializable {

    private static final long serialVersionUID = 7638856549367368548L;

    private String retCode;

    private String retMsg;

    private T data;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
