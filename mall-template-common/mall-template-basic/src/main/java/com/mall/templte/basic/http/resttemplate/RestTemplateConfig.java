package com.mall.templte.basic.http.resttemplate;

import java.nio.charset.StandardCharsets;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.mall.templte.basic.http.resttemplate.config.ConnectionSettings;

/**
 * 
 * @author yangkai3
 *
 */
@Configuration
@EnableConfigurationProperties({ ConnectionSettings.class })
public class RestTemplateConfig {

    public static final int STRING_HTTP_CONVERTER_INDEX = 1;

    @Autowired
    private ConnectionSettings connectionSettings;
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(getHttpRequestFactory());
        restTemplate.getMessageConverters().set(STRING_HTTP_CONVERTER_INDEX,
                new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(0,new FastJsonHttpMessageConverter());
        return restTemplate;
    }
    
    @Bean
    public RestTemplate ipRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(getHttpRequestFactory());
        restTemplate.getMessageConverters().set(STRING_HTTP_CONVERTER_INDEX,
                new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    public HttpComponentsClientHttpRequestFactory getHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(getHttpClient());
        return factory;
    }

    public HttpClient getHttpClient() {
        return HttpClientBuilder.create().setDefaultRequestConfig(getRequestConfig())
                .setConnectionManager(getPoolingHttpClientConnectionManager()).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "restTemplate.connectionManager")
    public PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        return connectionManager;
    }

    @Bean
    @ConfigurationProperties(prefix = "requestTemplate")
    public RequestConfig getRequestConfig() {
        RequestConfig requestConfig = RequestConfig.custom().build();
        return requestConfig;
    }
    @Bean
    @LoadBalanced
    @ConfigurationProperties(prefix = "asyncRestTemplate")
    public AsyncRestTemplate asyncRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectionSettings.getConnectionTimeout());
        requestFactory.setReadTimeout(connectionSettings.getReadTimeout());
        requestFactory.setTaskExecutor(threadPoolTaskScheduler());
        return new AsyncRestTemplate(requestFactory);
    }
    
    @Bean(destroyMethod="shutdown")
    ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(connectionSettings.getPoolSize()==0?1:connectionSettings.getPoolSize());
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }
}
