package com.celuma.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.celuma.webapi"})
public class CelumaWebapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CelumaWebapiApplication.class, args);
	}

}
