package com.rlti.financas.despesas.application.api;

import java.util.List;

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
//		if(despesaRequest.getParcela() == 1) {
//			log.info("[inicia] DespesaController - getParcela - " + despesaRequest.getParcela());
//		}
		log.info("[finaliza] DespesaController - postDespesa");		
		return despesaCriada;
	}

	@Override
	public List<DespesaListResponse> getTodasDespesas() {
		log.info("[inicia] DespesaController - getTodasDespesas");
		log.info("[inicia] DespesaController - getTodasDespesas");
		return null;
	}
}
