package com.rlti.financas.despesas.application.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

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
	LocalDate dataPagameto;
	
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
				despesaRequest.setDataPagamento(validaMes(despesaRequest.getDataPagamento(), count));				
				despesaRequest.setQuantidadePacelas(statusParcela);
				despesaRequest.setValor(valorParcela);
				despesaRepository.salva(new Despesa(despesaRequest));		
				log.info("[finaliza] - DespesaApplicationService - criaDespesaParcelada");						
			}
		//	return lista;	
		}
		return null;
	}	
	
	private LocalDate validaMes(LocalDate proximoMes, int count) {		
		if (count == 1) dataPagameto = proximoMes.plusMonths(0);		
		else if (count > 1) dataPagameto = proximoMes.plusMonths(1);
		return dataPagameto;			
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

	@Override
	public void patchAlteraDespesa(Long idDespesa, @Valid DespesaAlteracaoRequest despesaAlteracaoRequest) {
		log.info("[inicia] - DespesaApplicationService - patchAlteraDespesa");
		log.info("{idDespesa} - {}", idDespesa);
		Despesa despesa = despesaRepository.buscaDespesaAtravesId(idDespesa);
		despesa.altera(despesaAlteracaoRequest);
		despesaRepository.salva(despesa);
		log.info("[finaliza] - DespesaApplicationService - patchAlteraDespesa");		
	}

	@Override
	public List<DespesaListResponse> getDespesasPorData(LocalDate dataPagamento) {
		log.info("[inicia] - DespesaApplicationService - getDespesasPorData");
		List<Despesa> despesa = despesaRepository.buscaTodasDespesaPorData(dataPagamento);
		log.info("[finaliza] - DespesaApplicationService - getDespesasPorData");
		return DespesaListResponse.converte(despesa);
	}	
}
