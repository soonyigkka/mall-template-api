package com.mall.templte.basic.response.exception;

import com.mall.templte.basic.response.enums.IReturnEnum;

public class SystemErrorException  extends RuntimeException{
    
    private static final long serialVersionUID = 5329918284837171857L;

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

    public SystemErrorException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public SystemErrorException(IReturnEnum returnEnum){
        this.errorCode = returnEnum.getRetCode();
        this.errorMessage = returnEnum.getRetMsg();
    }

    public SystemErrorException getSystemErrorException(IReturnEnum returnEnum) {
        return new SystemErrorException(returnEnum.getRetCode(),returnEnum.getRetMsg());
    }
}
