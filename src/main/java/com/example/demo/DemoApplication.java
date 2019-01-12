package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * https://blog.csdn.net/iku5200/article/details/82856621
 *
 * https://blog.csdn.net/diaoling1990/article/details/82356747  // MyBatis
 */

@MapperScan("com.example.demo.mapper") //扫描的mapper
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

