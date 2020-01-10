package com.mall.templte.basic.helper.logger;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSONObject;

public abstract class AbstractHelper {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    protected HttpEntity<String> doParams(Object orignal){
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.valueOf("application/json"));
       HttpEntity<String> returnEntiry = new HttpEntity<>(JSONObject.toJSONString(orignal),headers);
       return returnEntiry;
    }
    
    protected String doUrl(String url,Object ... params) {
        return MessageFormat.format(url, params);
    }
}
