package org.aijie.helloworld;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.aijie.helloworld.dao"})

public class SpringbootDemoHelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoHelloworldApplication.class, args);
	}

}
