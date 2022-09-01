package com.rlti.financas.despesas.application.api;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.rlti.financas.despesas.domain.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DespesaRequest {
	private String descricao;
	@NotNull
	private Categoria categoria;
	@NotNull
	private int parcela;
	private String quantidadePacelas;
	@NotNull	
	private LocalDate dataPagamento;
	@NotNull 
	private Double valor;	
}

