package com.rlti.financas.despesas.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rlti.financas.despesas.application.api.DespesaAlteracaoRequest;
import com.rlti.financas.despesas.application.api.DespesaRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Despesa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idDespesa;
	@NotNull
	private String descricao;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	@NotNull	
	@Transient
	private int parcela;
	private String quantidadePacelas;
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataPagamento;
	@NotNull
	private Double valor;

	public Despesa(DespesaRequest despesaRequest) {
		this.descricao = despesaRequest.getDescricao();
		this.categoria = despesaRequest.getCategoria();		
		this.quantidadePacelas = despesaRequest.getQuantidadePacelas();
		this.dataPagamento = despesaRequest.getDataPagamento();
		this.valor = despesaRequest.getValor();
	}

	public void altera(@Valid DespesaAlteracaoRequest despesaAlteracaoRequest) {
		this.descricao = despesaAlteracaoRequest.getDescricao();
		this.categoria = despesaAlteracaoRequest.getCategoria();
		this.dataPagamento = despesaAlteracaoRequest.getDataPagamento();
		this.valor = despesaAlteracaoRequest.getValor();		
	}
}
