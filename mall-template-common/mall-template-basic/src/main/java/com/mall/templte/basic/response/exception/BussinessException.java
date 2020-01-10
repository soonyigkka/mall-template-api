package com.mall.templte.basic.response.exception;

import com.mall.templte.basic.response.enums.IReturnEnum;
/**
 * 
 * @author yangkai3
 *
 */
public class BussinessException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1509475378354931320L;

    private String errorCode;

    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BussinessException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public BussinessException(IReturnEnum returnEnum){
        this.errorCode = returnEnum.getRetCode();
        this.errorMessage = returnEnum.getRetMsg();
    }

    public BussinessException getBussinessException(IReturnEnum returnEnum) {
        return new BussinessException(returnEnum.getRetCode(),returnEnum.getRetMsg());
    }
}
