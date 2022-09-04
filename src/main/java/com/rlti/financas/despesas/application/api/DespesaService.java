package com.rlti.financas.despesas.application.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

public interface DespesaService {
	DespesaResponse criaDespesa(DespesaRequest despesaRequest);
	List<DespesaListResponse> buscaTodasDespesas();
	DespesaDetalhadoResponse buscaClienteAtravesId(Long idDespesa);
	void deletaDespesaAtravesId(Long idDespesa);
	void patchAlteraDespesa(Long idDespesa, @Valid DespesaAlteracaoRequest despesaAlteracaoRequest);
	List<DespesaListResponse> getDespesasPorData(LocalDate dataPagamento);
	List<DespesaListResponse> buscaTodasDespesasPeriodo(LocalDate dataInicial, LocalDate dataFinal);

}
