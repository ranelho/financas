package com.rlti.financas.despesas.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rlti.financas.despesas.application.repository.DespesaRepository;
import com.rlti.financas.despesas.domain.Despesa;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class DespesaInfraRepository implements DespesaRepository {

	private final DespesaSpringDataJPARepository despesaSpringDataJPARepository;

	@Override
	public Despesa salva(Despesa despesa) {
		log.info("[inicia] - DespesaInfraRepository - salva");
		despesaSpringDataJPARepository.save(despesa);
		log.info("[finaliza] - DespesaInfraRepository - salva");
		return despesa;
	}

	@Override
	public List<Despesa> buscaTodasDespesa() {
		log.info("[inicia] - DespesaInfraRepository - buscaTodasDespesa");
		List<Despesa> todasDispesas = despesaSpringDataJPARepository.findAll();
		log.info("[finaliza] - DespesaInfraRepository - buscaTodasDespesa");
		return todasDispesas;
	}
}
