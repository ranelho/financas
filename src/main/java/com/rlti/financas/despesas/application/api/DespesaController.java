package com.rlti.financas.despesas.application.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class DespesaController implements DespesaApi {

	@Override
	public DespesaResponse postDespesa(@Valid DespesaRequest despesaRequest) {
		log.info("[inicia] DespesaController - postDespesa");
		log.info("[finaliza] DespesaController - postDespesa");
		
		return null;
	}

}
