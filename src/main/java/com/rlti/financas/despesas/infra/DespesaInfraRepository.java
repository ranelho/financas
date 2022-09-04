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

	@Override
	public Despesa buscaDespesaAtravesId(Long idDespesa) {
		log.info("[inicia] - DespesaInfraRepository - buscaDespesaAtravesId");
		Despesa despesa = despesaSpringDataJPARepository.findById(idDespesa)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND,"Despesa não encontrada!"));;
		log.info("[finaliza] - DespesaInfraRepository - buscaDespesaAtravesId");
		return despesa;
	}

	@Override
	public void deletaDespesa(Despesa despesa) {
		log.info("[inicia] DespesaInfraRepository - deletaDespesa");
		despesaSpringDataJPARepository.delete(despesa);
		log.info("[finaliza] DespesaInfraRepository - deletaDespesa");		
	}

	@Override
	public List<Despesa> buscaTodasDespesaPorData(LocalDate dataPagamento) {
		log.info("[inicia] DespesaInfraRepository - buscaTodasDespesaPorData");
		List<Despesa> despesa = despesaSpringDataJPARepository.findAllPorDataPagamento(dataPagamento);
		log.info("[finaliza] DespesaInfraRepository - buscaTodasDespesaPorData");
		return despesa;
	}

	@Override
	public List<Despesa> buscaTodasDespesaPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		log.info("[inicia] DespesaInfraRepository - buscaTodasDespesaPeriodo");
		List<Despesa> despesa = despesaSpringDataJPARepository.findALLPeriodo(dataInicial, dataFinal);
		log.info("[final] DespesaInfraRepository - buscaTodasDespesaPeriodo");
		return despesa;
	}

	
}
