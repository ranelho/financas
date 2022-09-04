package com.rlti.financas.despesas.application.repository;

import java.time.LocalDate;
import java.util.List;

import com.rlti.financas.despesas.domain.Despesa;

public interface DespesaRepository {
	Despesa salva(Despesa despesa);
	List<Despesa> buscaTodasDespesa();
	Despesa buscaDespesaAtravesId(Long idDespesa);
	void deletaDespesa(Despesa despesa);
	List<Despesa> buscaTodasDespesaPorData(LocalDate dataPagamento);
}
