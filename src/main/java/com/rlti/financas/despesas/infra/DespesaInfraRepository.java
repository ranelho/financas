package com.rlti.financas.despesas.infra;

import org.springframework.stereotype.Repository;

import com.rlti.financas.despesas.application.repository.DespesaRepository;
import com.rlti.financas.despesas.domain.Despesa;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class DespesaInfraRepository implements DespesaRepository {

	@Override
	public Despesa salva(Despesa despesa) {
		log.info("[inicia] - DespesaInfraRepository - salva");
		log.info("[finaliza] - DespesaInfraRepository - salva");
		return despesa;
	}

}
