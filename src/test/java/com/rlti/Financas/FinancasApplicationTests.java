package com.rlti.Financas;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.rlti.financas.despesas.application.api.DespesaListResponse;

@SpringBootTest
class FinancasApplicationTests {

	@Test
	void contextLoads() {		
		
		
		List<DespesaListResponse> despesas = new ArrayList<DespesaListResponse>();
		
		despesas.add(new DespesaListResponse("1/2", 50.0));
		despesas.add(new DespesaListResponse("2/2", 50.0));
		despesas.add(new DespesaListResponse("1/1", 25.0));
		despesas.add(new DespesaListResponse("1/1", 10.0));
		
	/*	Double somaTotal = despesas.stream()
				.mapToDouble(DespesaListResponse::getValor)
				.sum();			
	*/
				
		Double soma = DespesaListResponse.SomaValor(despesas);
		System.out.println(soma);
	}	

}
