package com.mall.templte.basic.service;

import com.mall.templte.basic.helper.logger.AbstractHelper;
import com.mall.templte.basic.response.enums.SystemCodeEnum;
import com.mall.templte.basic.response.exception.BussinessException;

public abstract class AbstractBaseService extends AbstractHelper {
    
    public Object fallback() throws BussinessException{
        throw new BussinessException(SystemCodeEnum.SERVICE_REDUCE_ERROR);
    }
}
