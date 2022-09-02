package com.rlti.financas.despesas.application.api;

import java.util.List;

public interface DespesaService {
	DespesaResponse criaDespesa(DespesaRequest despesaRequest);
	List<DespesaListResponse> buscaTodasDespesas();
}
