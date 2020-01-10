package com.mall.templte.basic.response.exception.advice;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.templte.basic.response.enums.SystemCodeEnum;
import com.mall.templte.basic.response.exception.BussinessException;
import com.mall.templte.basic.response.exception.SystemErrorException;
import com.mall.templte.basic.response.helper.ResponseHelper;

@ControllerAdvice
public class GlobleExceptionAdvice {
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String runExceptionHandler(RuntimeException exception) {
        logger.error("system error",exception);
        return ResponseHelper.productSystemErrorResponse().toJsonString();
    }
    
    @ExceptionHandler(BussinessException.class)
    @ResponseBody
    public String runBussinessExceptionHandler(BussinessException exception) {
        logger.error("BussinessException",exception);
        return ResponseHelper.productResponse(exception).toJsonString();
    }
    
    @ExceptionHandler(SystemErrorException.class)
    @ResponseBody
    public String runSystemErrorExceptionHandler(SystemErrorException exception) {
        logger.error("SystemErrorException",exception);
        return ResponseHelper.productResponse(exception).toJsonString();
    }
    
    
    public static final String RETURN_VALIDATE_MESSAGE = "[field:{0}];[message:{1}]";
    
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public String runBindExceptionHandler(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> objectErrors = bindingResult.getAllErrors();
        if( objectErrors!=null && objectErrors.size()>0 ) {
            ObjectError objectError = objectErrors.get(0);
            String message = "";
            if(objectErrors instanceof FieldError) {
                FieldError fieldError = (FieldError) objectErrors;
                message = MessageFormat.format(RETURN_VALIDATE_MESSAGE, fieldError.getField(),objectError.getDefaultMessage());
            }else {
                message = objectError.getDefaultMessage();
            }
            return ResponseHelper.productResponse(SystemCodeEnum.PARAM_FORMAT_ERROR.getRetCode(),message).toJsonString();
        }
        return ResponseHelper.productResponse(SystemCodeEnum.PARAM_FORMAT_ERROR).toJsonString();
    }
}
