package com.mall;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;




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

	public static void main(String[] args) {
		SpringApplication.run(TeacherApplication.class, args);
	}
}
