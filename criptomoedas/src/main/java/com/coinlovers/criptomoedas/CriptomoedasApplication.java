package com.coinlovers.criptomoedas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CriptomoedasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriptomoedasApplication.class, args);
	}

}
