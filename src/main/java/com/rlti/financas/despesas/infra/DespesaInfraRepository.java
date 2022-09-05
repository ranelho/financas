package com.rlti.financas.despesas.infra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.rlti.financas.despesas.application.repository.DespesaRepository;
import com.rlti.financas.despesas.domain.Despesa;
import com.rlti.financas.handler.APIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class DespesaInfraRepository implements DespesaRepository {

	private final DespesaSpringDataJPARepository despesaSpringDataJPARepository;

	@Override
	public Despesa salva(Despesa despesas) {
		log.info("[inicia] - DespesaInfraRepository - salva");
		despesaSpringDataJPARepository.save(despesas);
		log.info("[finaliza] - DespesaInfraRepository - salva");
		return despesas;
	}

	@Override
	public List<Despesa> buscaTodasDespesa() {
		log.info("[inicia] - DespesaInfraRepository - buscaTodasDespesa");
		List<Despesa> todasDespesas = despesaSpringDataJPARepository.findAll();
		log.info("[finaliza] - DespesaInfraRepository - buscaTodasDespesa");
		return todasDespesas;
	}

	@Override
	public Despesa buscaDespesaAtravesId(Long idDespesa) {
		log.info("[inicia] - DespesaInfraRepository - buscaDespesaAtravesId");
		Despesa despesas = despesaSpringDataJPARepository.findById(idDespesa)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND,"Despesa não encontrada!"));
		log.info("[finaliza] - DespesaInfraRepository - buscaDespesaAtravesId");
		return despesas;
	}

	@Override
	public void deletaDespesa(Despesa despesas) {
		log.info("[inicia] DespesaInfraRepository - deletaDespesa");
		despesaSpringDataJPARepository.delete(despesas);
		log.info("[finaliza] DespesaInfraRepository - deletaDespesa");		
	}

	@Override
	public List<Despesa> buscaTodasDespesaPorData(LocalDate dataPagamento) {
		log.info("[inicia] DespesaInfraRepository - buscaTodasDespesaPorData");
		List<Despesa> despesas = despesaSpringDataJPARepository.findAllPorDataPagamento(dataPagamento);
		log.info("[finaliza] DespesaInfraRepository - buscaTodasDespesaPorData");		
		return despesas;
	}

	@Override
	public List<Despesa> buscaTodasDespesaPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		log.info("[inicia] DespesaInfraRepository - buscaTodasDespesaPeriodo");
		List<Despesa> despesas = despesaSpringDataJPARepository.findALLPeriodo(dataInicial, dataFinal);
		log.info("[final] DespesaInfraRepository - buscaTodasDespesaPeriodo");
		return despesas;
	}
	
}
