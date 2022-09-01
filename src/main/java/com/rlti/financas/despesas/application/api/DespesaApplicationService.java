package com.rlti.financas.despesas.application.api;

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

	@Override
	public DespesaResponse criaDespesa(DespesaRequest despesaRequest) {
		log.info("[inicia] - DespesaResponse - criaDespesa");
		if (despesaRequest.getParcela() == 1) {
			String statusParcela = "1/1";
			despesaRequest.setQuantidadePacelas(statusParcela);
			Despesa despesa = despesaRepository.salva(new Despesa(despesaRequest));
			log.info("[finaliza] - DespesaResponse - criaDespesa");
			return DespesaResponse.builder().idDespesa(despesa.getIdDespesa()).build();

		} else if (despesaRequest.getParcela() > 1) {
			double valorParcela = despesaRequest.getValor() / despesaRequest.getParcela();
			for (int count = 1; count <= despesaRequest.getParcela(); count++) {
				String statusParcela = count + "/" + despesaRequest.getParcela();
				despesaRequest.setQuantidadePacelas(statusParcela);
				despesaRequest.setValor(valorParcela);
				despesaRepository.salva(new Despesa(despesaRequest));
				log.info("[finaliza] - DespesaResponse - criaDespesa");
			}
		}
		return null;
	}
}
