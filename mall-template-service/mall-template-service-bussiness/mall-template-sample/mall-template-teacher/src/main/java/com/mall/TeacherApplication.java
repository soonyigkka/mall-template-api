package com.mall;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * teacher启动入口类
 * @author csu
 *
 */
@SpringBootApplication
@MapperScan("com.mall.template.**.dao")
@EnableEurekaClient
@EnableCircuitBreaker
public class TeacherApplication {
	
	
	@Bean(name="dataSource",initMethod="init")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource(){
		DataSource dataSource = new DruidDataSource();
		return dataSource;
	}
	
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(TeacherApplication.class, args);
	}
}
