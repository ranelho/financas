package com.rlti.financas.despesas.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Despesa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "uuid", name = "idDespesa", updatable = false, unique = true, nullable = false)
	private UUID idDespesa;
	@NotNull
	private String descricao;
	@NotNull
	private Categoria categoria;
	@NotNull
	private LocalDate dataPagamento;
	
}
