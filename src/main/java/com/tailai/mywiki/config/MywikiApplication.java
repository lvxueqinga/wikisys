package com.tailai.mywiki.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tailai")
@MapperScan("com.tailai.mywiki.dao")
public class MywikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MywikiApplication.class, args);
	}

}
