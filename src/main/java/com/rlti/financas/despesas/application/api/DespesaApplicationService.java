package com.rlti.financas.despesas.application.api;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DespesaApplicationService implements DespesaService {

	@Override
	public DespesaResponse criaDespesa(DespesaRequest despesaRequest) {
		log.info("[inicia] - DespesaResponse - criaDespesa");
		log.info("[finaliza] - DespesaResponse - criaDespesa");
		return null;
	}

}
