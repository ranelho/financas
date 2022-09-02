package com.rlti.financas.despesas.application.repository;

import java.util.List;

import com.rlti.financas.despesas.domain.Despesa;

public interface DespesaRepository {
	Despesa salva(Despesa despesa);
	List<Despesa> buscaTodasDespesa();
}
