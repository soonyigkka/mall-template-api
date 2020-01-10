package com.mall.templte.basic.response.enums;

public enum BussinessCodeEnum implements IReturnEnum {

	AGE_INVALID("3B210001", "年龄不合法！");

    private String retCode;

    private String retMsg;

    private BussinessCodeEnum(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

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
}
