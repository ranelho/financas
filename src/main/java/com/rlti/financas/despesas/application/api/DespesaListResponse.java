package com.rlti.financas.despesas.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.rlti.financas.despesas.domain.Categoria;
import com.rlti.financas.despesas.domain.Despesa;

import lombok.Value;

@Value
public class DespesaListResponse {
	private Long idDespesa;
	private String descricao;
	private Categoria categoria;
	private String quantidadePacelas;
	private LocalDate dataPagamento;
	private Double valor;
	
	public static List<DespesaListResponse> converte(List<Despesa> despesas) {
		return despesas.stream()
				.map(DespesaListResponse::new)
				.collect(Collectors.toList());
	}
	
	private DespesaListResponse(Despesa despesa) {
		this.idDespesa = despesa.getIdDespesa();
		this.descricao = despesa.getDescricao();
		this.categoria = despesa.getCategoria();
		this.quantidadePacelas = despesa.getQuantidadePacelas();
		this.dataPagamento = despesa.getDataPagamento();
		this.valor = despesa.getValor();
	}
	
}
