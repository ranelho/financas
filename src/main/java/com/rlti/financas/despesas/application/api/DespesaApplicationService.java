package com.rlti.financas.despesas.application.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rlti.financas.despesas.application.repository.DespesaRepository;
import com.rlti.financas.despesas.domain.Despesa;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class DespesaApplicationService implements DespesaService {

	private final DespesaRepository despesaRepository;
	String statusParcela;
	LocalDate proximoMes;
	
	@Override
	public DespesaResponse criaDespesa(DespesaRequest despesaRequest) {
		log.info("[inicia] - DespesaApplicationService - criaDespesa");
		
		if (despesaRequest.getParcela() == 1) {
			statusParcela = "1/1";
			despesaRequest.setQuantidadePacelas(statusParcela);
			Despesa despesa = despesaRepository.salva(new Despesa(despesaRequest));
			log.info("[finaliza] - DespesaApplicationService - criaDespesaUnica");
			return DespesaResponse.builder().idDespesa(despesa.getIdDespesa()).build();
			
		} else if (despesaRequest.getParcela() > 1) {
			
			double valorParcela = despesaRequest.getValor() / despesaRequest.getParcela();	
			
			for (int count = 1; count <= despesaRequest.getParcela(); count++) {
				
				statusParcela = count + "/" + despesaRequest.getParcela();
				if (count > 1) {
					proximoMes = despesaRequest.getDataPagamento().plusMonths(1);	
					despesaRequest.setDataPagamento(proximoMes);
				}			
				despesaRequest.setQuantidadePacelas(statusParcela);
				despesaRequest.setValor(valorParcela);
				despesaRepository.salva(new Despesa(despesaRequest));				
				log.info("[finaliza] - DespesaApplicationService - criaDespesaParcelada");				
			}
		}
		return null;
	}

	@Override
	public List<DespesaListResponse> buscaTodasDespesas() {
		log.info("[inicia] - DespesaApplicationService - buscaTodasDespesas");
		List<Despesa> despesas = despesaRepository.buscaTodasDespesa();
		log.info("[finaliza] - DespesaApplicationService - buscaTodasDespesas");
		return DespesaListResponse.converte(despesas);
	}

	@Override
	public DespesaDetalhadoResponse buscaClienteAtravesId(Long idDespesa) {
		log.info("[inicia] - DespesaApplicationService - buscaClienteAtravesId");
		log.info("{idDespesa} - {}", idDespesa);
		Despesa despesa = despesaRepository.buscaDespesaAtravesId(idDespesa);
		log.info("[finaliza] - DespesaApplicationService - buscaClienteAtravesId");
		return new DespesaDetalhadoResponse(despesa);
	}

	@Override
	public void deletaDespesaAtravesId(Long idDespesa) {
		log.info("[inicia] DespesaApplicationService");
		Despesa despesa = despesaRepository.buscaDespesaAtravesId(idDespesa);
		despesaRepository.deletaDespesa(despesa);
	}	
}
