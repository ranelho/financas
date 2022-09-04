package com.rlti.financas.despesas.application.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/despesa")
public interface DespesaApi {

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	DespesaResponse postDespesa(@Valid @RequestBody DespesaRequest despesaRequest);

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<DespesaListResponse> getTodasDespesas();

	@GetMapping(value = "/{idDespesa}")
	@ResponseStatus(code = HttpStatus.OK)
	DespesaDetalhadoResponse getDespesaAtravesId(@PathVariable Long idDespesa);

	@GetMapping(value = "/dataPagamento/{dataPagamento}")
	@ResponseStatus(code = HttpStatus.OK)
	List<DespesaListResponse> getDespesasPorData(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPagamento);

	@GetMapping(value = "/periodo/{dataInicial},{dataFinal}")
	@ResponseStatus(code = HttpStatus.OK)
	List<DespesaListResponse> getDespesasPorPeriodo(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial,
													@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal);
	
	@DeleteMapping(value = "/{idDespesa}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaDespesaAtravesId(@PathVariable Long idDespesa);

	@PatchMapping(value = "/{idDespesa}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void patchAlteraDespesa(@PathVariable Long idDespesa,
	@Valid @RequestBody DespesaAlteracaoRequest despesaAlteracaoRequest);
}
