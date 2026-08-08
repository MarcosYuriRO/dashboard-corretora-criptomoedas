package com.coinlovers.criptomoedas.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coinlovers.criptomoedas.client.BinanceAPIClient;
import com.coinlovers.criptomoedas.dto.client.BinanceCriptoUltimas24HorasResponse;
import com.coinlovers.criptomoedas.dto.response.CriptoUltimas24HorasResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CriptomoedaService {
	
	private final String MOEDA_COTACAO_PADRAO = "BRL";
	private final int LIMITE_CRIPTOMOEDAS = 10;
	
	private BinanceAPIClient client;

	public CriptomoedaService(BinanceAPIClient client) {
		this.client = client;
	}
	
	public List<CriptoUltimas24HorasResponse> consultaCriptomoeda(String criptomoeda) {
		String simbolo = criptomoeda + MOEDA_COTACAO_PADRAO;
		log.info("Consultando o sistema da Binance sobre o preço da criptomoeda {}.", simbolo);
		
		List<BinanceCriptoUltimas24HorasResponse> response = client.consultarUltimas24Horas();
		
		log.info("Consulta de preço realizada!");
		
		if (criptomoeda == null) {
			return response.stream()
					.filter(cripto -> cripto.criptomoeda().endsWith(MOEDA_COTACAO_PADRAO))
					.map(cripto -> build(cripto))
					.toList();
		}
		
		return response.stream()
				.filter(cripto -> cripto.criptomoeda().contains(criptomoeda.concat(MOEDA_COTACAO_PADRAO)))
				.map(cripto -> build(cripto))
				.toList();
		
	}
	
	public List<CriptoUltimas24HorasResponse> consultaCriptomoedasUltimas24Horas() {
		
		
		List<BinanceCriptoUltimas24HorasResponse> response = client.consultarUltimas24Horas();
		
		return response.stream()
				.filter(cripto -> cripto.criptomoeda().endsWith(MOEDA_COTACAO_PADRAO))
				.sorted(Comparator.comparing(BinanceCriptoUltimas24HorasResponse::porcentagemMudancaPreco).reversed())
				.map(cripto -> build(cripto))
				.limit(LIMITE_CRIPTOMOEDAS)
				.toList();
	}
	
	private CriptoUltimas24HorasResponse build(BinanceCriptoUltimas24HorasResponse binanceCripto) {
		return CriptoUltimas24HorasResponse
		.builder()
		.criptoMoeda(binanceCripto.criptomoeda())
		.ultimoPreco(binanceCripto.ultimoPreco())
		.porcentagemMudancaPreco(binanceCripto.porcentagemMudancaPreco())
		.volume(binanceCripto.volume())
		.build();
	}
	
}
