package com.rlti.financas.despesas.infra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rlti.financas.despesas.domain.Despesa;

public interface DespesaSpringDataJPARepository extends JpaRepository<Despesa, Long>{

	@Query(value = "SELECT * FROM despesa t WHERE data_pagamento = ?1 ", nativeQuery = true)	
	List<Despesa> findAllPorDataPagamento(LocalDate dataPagamento);

}

