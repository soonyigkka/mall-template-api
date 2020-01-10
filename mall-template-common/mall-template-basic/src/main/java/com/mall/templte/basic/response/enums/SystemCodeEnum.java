package com.mall.templte.basic.response.enums;

/**
 * 系统返回码
 * 
 * @author yangkai3
 *
 */
public enum SystemCodeEnum implements IReturnEnum {
    SUCCESS("00000000", "success"), 
    SYSTEM_ERROR("99999999", "系统异常！"), 
    PARAM_FORMAT_ERROR("99999998","参数异常！"), 
    SERVICE_REDUCE_ERROR("99999997", "当前排队人数过多，请稍后再试！"),
    HYSTRIX_INNER_ERROR("99999995", "系统进入熔断状态");

    private String retCode;

    private String retMsg;

    private SystemCodeEnum(String retCode, String retMsg) {
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
