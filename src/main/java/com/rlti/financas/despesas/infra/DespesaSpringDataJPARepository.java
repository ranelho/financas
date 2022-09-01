package com.rlti.financas.despesas.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rlti.financas.despesas.domain.Despesa;

public interface DespesaSpringDataJPARepository extends JpaRepository<Despesa, Long>{

}

