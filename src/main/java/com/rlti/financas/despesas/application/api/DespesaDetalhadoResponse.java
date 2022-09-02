package com.rlti.financas.despesas.application.api;

import java.time.LocalDate;

import com.rlti.financas.despesas.domain.Categoria;

import lombok.Value;

@Value
public class DespesaDetalhadoResponse {

	private Long idDespesa;
	private String descricao;
	private Categoria categoria;
	private String quantidadePacelas;
	private LocalDate dataPagamento;
	private Double valor;
	
	
	
}
