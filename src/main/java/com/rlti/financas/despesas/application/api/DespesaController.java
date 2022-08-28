package com.rlti.financas.despesas.application.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class DespesaController implements DespesaApi {
	
	private final DespesaService despesaService;

	@Override
	public DespesaResponse postDespesa(@Valid DespesaRequest despesaRequest) {
		log.info("[inicia] DespesaController - postDespesa");
		DespesaResponse despesaCriada = despesaService.criaDespesa(despesaRequest);
		log.info("[finaliza] DespesaController - postDespesa");		
		return despesaCriada;
	}
}
