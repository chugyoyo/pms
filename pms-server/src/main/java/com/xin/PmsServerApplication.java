package com.xin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class PmsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsServerApplication.class, args);
	}

}
