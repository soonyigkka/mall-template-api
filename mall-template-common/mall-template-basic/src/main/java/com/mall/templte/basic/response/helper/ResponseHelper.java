package com.mall.templte.basic.response.helper;

import com.mall.templte.basic.response.enums.IReturnEnum;
import com.mall.templte.basic.response.enums.SystemCodeEnum;
import com.mall.templte.basic.response.exception.BussinessException;
import com.mall.templte.basic.response.exception.SystemErrorException;
import com.mall.templte.basic.response.pojo.ResponseInfo;

/**
 * 返回码处理类
 * 
 * @author yangkai3
 *
 */
public class ResponseHelper {
    
    public static <T> ResponseInfo<T> productSuccessResponse(T data){
        return productResponse(SystemCodeEnum.SUCCESS,data);
    }
    
    public static <T> ResponseInfo<T> productSuccessResponse(){
        return productSuccessResponse(null);
    }
    
    public static <T> ResponseInfo<T> productSystemErrorResponse(){
        return productResponse(SystemCodeEnum.SYSTEM_ERROR);
    }
    
    public static <T> ResponseInfo<T> productSystemErrorResponse(String errorMessage){
        return productResponse(SystemCodeEnum.SYSTEM_ERROR.getRetCode(),errorMessage);
    }
    
    public static <T> ResponseInfo<T> productResponse(IReturnEnum returnEnum){
        return productResponse(returnEnum.getRetCode(), returnEnum.getRetMsg());
    }
    
    public static <T> ResponseInfo<T> productResponse(IReturnEnum returnEnum,T data){
        return productResponse(returnEnum.getRetCode(), returnEnum.getRetMsg(),data);
    }
    
    public static <T> ResponseInfo<T>  productResponse(String retCode,String retMsg,T data){
        ResponseInfo<T> result = new ResponseInfo<>();
        result.setRetCode(retCode);
        result.setRetMsg(retMsg);
        result.setData(data);
        return result;
    }
    
    public static <T> ResponseInfo<T>  productResponse(String retCode,String retMsg){
        return productResponse(retCode, retMsg, null);
    }
    
    public static <T> ResponseInfo<T>  productResponse(BussinessException exception){
        return productResponse(exception.getErrorCode(),exception.getErrorMessage());
    }
    
    public static <T> ResponseInfo<T>  productResponse(SystemErrorException exception){
        return productResponse(exception.getErrorCode(),exception.getErrorMessage());
    }
    
    public static boolean isSuccess(String retCode) {
        if(SystemCodeEnum.SYSTEM_ERROR.getRetCode().equals(retCode)) {
            return true;
        }
        return false;
    }
    
}
