package com.rlti.financas;

import java.text.ParseException;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class FinancasApplication {

	@GetMapping
	public String getHomeTeste() throws ParseException {

		LocalDate hoje = LocalDate.now();
		System.out.println(hoje);
		LocalDate proximoMes = hoje.plusMonths(1);
		System.out.println(proximoMes);
		
		return "Despesa - API Home";
		
	}

	public static void main(String[] args) {
		SpringApplication.run(FinancasApplication.class, args);
	}

}
