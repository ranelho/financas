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
		log.info("[finaliza] DespesaController - postDespesa");		
		return despesaCriada;
	}

	@Override
	public List<DespesaListResponse> getTodasDespesas() {
		log.info("[inicia] DespesaController - getTodasDespesas");
		List<DespesaListResponse> despesas = despesaService.buscaTodasDespesas();
		log.info("[finaliza] DespesaController - getTodasDespesas");
		return despesas;
	}

	@Override
	public DespesaDetalhadoResponse getDespesaAtravesId(Long idDespesa) {
		log.info("[inicia] DespesaController - getDespesaAtravesId");
		log.info("[idDespesa] {}", idDespesa);		
		DespesaDetalhadoResponse despesaDetalhadoResponse = despesaService.buscaClienteAtravesId(idDespesa);
		log.info("[finaliza] DespesaController - getDespesaAtravesId");
		return despesaDetalhadoResponse;
	}

	@Override
	public void deletaDespesaAtravesId(Long idDespesa) {
		log.info("[inicia] DespesaController - deletaDespesaAtravesId");
		log.info("[idDespesa] {}", idDespesa);	
		despesaService.deletaDespesaAtravesId(idDespesa);
		log.info("[finaliza] DespesaController - deletaDespesaAtravesId");
	}

	
}
