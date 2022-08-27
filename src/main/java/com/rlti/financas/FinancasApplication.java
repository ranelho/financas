package com.rlti.financas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class FinancasApplication {

	@GetMapping
	public String getHomeTeste() {
		return "Despesa - API Home";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FinancasApplication.class, args);
	}

}
