package com.rlti.financas.despesas.application.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rlti.financas.despesas.application.repository.DespesaRepository;
import com.rlti.financas.despesas.domain.Despesa;
import com.rlti.financas.despesas.domain.ValidaMes;
import com.rlti.financas.handler.APIException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class DespesaApplicationService implements DespesaService {

	private final DespesaRepository despesaRepository;
	String statusParcela;
	LocalDate dataPagamento;

	@Override
	public DespesaResponse criaDespesa(DespesaRequest despesaRequest) {
		log.info("[inicia] - DespesaApplicationService - criaDespesa");
		if (despesaRequest.getParcela() == 1) {
			statusParcela = "1/1";
			despesaRequest.setQuantidadePacelas(statusParcela);
			Despesa despesas = despesaRepository.salva(new Despesa(despesaRequest));
			log.info("[finaliza] - DespesaApplicationService - criaDespesaUnica");
			return DespesaResponse.builder().idDespesa(despesas.getIdDespesa()).build();

		} else if (despesaRequest.getParcela() > 1) {
			double valorParcela = despesaRequest.getValor() / despesaRequest.getParcela();
			for (int count = 1; count <= despesaRequest.getParcela(); count++) {
				statusParcela = count + "/" + despesaRequest.getParcela();
				despesaRequest.setDataPagamento(ValidaMes.validaMes(despesaRequest.getDataPagamento(), count));
				despesaRequest.setQuantidadePacelas(statusParcela);
				despesaRequest.setValor(valorParcela);
				despesaRepository.salva(new Despesa(despesaRequest));
				log.info("[finaliza] - DespesaApplicationService - criaDespesaParcelada");
			}
			// return lista;
		}
		return null;
	}

	@Override
	public List<DespesaListResponse> buscaTodasDespesas() {
		log.info("[inicia] - DespesaApplicationService - buscaTodasDespesas");
		List<Despesa> despesas = despesaRepository.buscaTodasDespesa();
		log.info("[finaliza] - DespesaApplicationService - buscaTodasDespesas");
		if (despesas.isEmpty()) {
			throw APIException.build(HttpStatus.NOT_FOUND, "Nenhuma despesa econtrada!");
		} else {
			return DespesaListResponse.converte(despesas);
		}

	}

	@Override
	public DespesaDetalhadoResponse buscaClienteAtravesId(Long idDespesa) {
		log.info("[inicia] - DespesaApplicationService - buscaClienteAtravesId");
		log.info("{idDespesa} - {}", idDespesa);
		Despesa despesas = despesaRepository.buscaDespesaAtravesId(idDespesa);
		log.info("[finaliza] - DespesaApplicationService - buscaClienteAtravesId");
		return new DespesaDetalhadoResponse(despesas);
	}

	@Override
	public void deletaDespesaAtravesId(Long idDespesa) {
		log.info("[inicia] DespesaApplicationService");
		Despesa despesa = despesaRepository.buscaDespesaAtravesId(idDespesa);
		despesaRepository.deletaDespesa(despesa);
	}

	@Override
	public void patchAlteraDespesa(Long idDespesa, @Valid DespesaAlteracaoRequest despesaAlteracaoRequest) {
		log.info("[inicia] - DespesaApplicationService - patchAlteraDespesa");
		log.info("{idDespesa} - {}", idDespesa);
		Despesa despesas = despesaRepository.buscaDespesaAtravesId(idDespesa);
		despesas.altera(despesaAlteracaoRequest);
		despesaRepository.salva(despesas);
		log.info("[finaliza] - DespesaApplicationService - patchAlteraDespesa");
	}

	@Override
	public List<DespesaListResponse> getDespesasPorData(LocalDate dataPagamento) {
		log.info("[inicia] - DespesaApplicationService - getDespesasPorData");
		List<Despesa> despesas = despesaRepository.buscaTodasDespesaPorData(dataPagamento);
		log.info("[finaliza] - DespesaApplicationService - getDespesasPorData");
		if (despesas.isEmpty()) {
			throw APIException.build(HttpStatus.NOT_FOUND, "Nenhuma despesa econtrada!");
		} else {
			return DespesaListResponse.converte(despesas);
		}
	}

	@Override
	public List<DespesaListResponse> buscaTodasDespesasPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		log.info("[inicia] - DespesaApplicationService - buscaTodasDespesasPeriodo");
		List<Despesa> despesas = despesaRepository.buscaTodasDespesaPeriodo(dataInicial, dataFinal);
		log.info("[final] - DespesaApplicationService - buscaTodasDespesasPeriodo");
		if (despesas.isEmpty()) {
			throw APIException.build(HttpStatus.NOT_FOUND, "Nenhuma despesa econtrada!");
		} else {
			return DespesaListResponse.converte(despesas);
		}		
	}

}
