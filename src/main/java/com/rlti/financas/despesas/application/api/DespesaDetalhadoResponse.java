package com.rlti.financas.despesas.application.api;

import java.time.LocalDate;

import com.rlti.financas.despesas.domain.Categoria;
import com.rlti.financas.despesas.domain.Despesa;

import lombok.Value;

@Value
public class DespesaDetalhadoResponse {

	
	private Long idDespesa;
	private String descricao;
	private Categoria categoria;
	private String quantidadePacelas;
	private LocalDate dataPagamento;
	private Double valor;
	
	public DespesaDetalhadoResponse(Despesa despesa) {
		this.idDespesa = despesa.getIdDespesa();
		this.descricao = despesa.getDescricao();
		this.categoria = despesa.getCategoria();
		this.quantidadePacelas = despesa.getQuantidadePacelas();
		this.dataPagamento = despesa.getDataPagamento();
		this.valor = despesa.getValor();
	}	
}
