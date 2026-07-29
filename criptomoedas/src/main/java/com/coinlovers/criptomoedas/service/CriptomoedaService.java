package com.coinlovers.criptomoedas.service;

import org.springframework.stereotype.Service;

import com.coinlovers.criptomoedas.client.BinanceAPIClient;
import com.coinlovers.criptomoedas.dto.client.BinanceCriptoPrecoResponse;
import com.coinlovers.criptomoedas.dto.response.CriptoPrecoResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CriptoMoedaService {
	
	private final String MOEDA_COTACAO_PADRAO = "BRL";
	
	private BinanceAPIClient client;

	public CriptoMoedaService(BinanceAPIClient client) {
		this.client = client;
	}
	
	public CriptoPrecoResponse consultarPreco(String criptoMoeda, String moedaCotacao) {
		if (moedaCotacao == null) {
			moedaCotacao = MOEDA_COTACAO_PADRAO;
		}
		
		String simbolo = criptoMoeda + moedaCotacao;
		log.info("Consultando o sistema da Binance sobre o preço da criptomoeda {}.", simbolo);
		
		BinanceCriptoPrecoResponse response = client.consultarPreco(simbolo);
		
		log.info("Consulta de preço realizada!");
		
		return CriptoPrecoResponse.builder()
				.criptoMoeda(response.criptoMoeda())
				.preco(response.preco())
				.build();
	}
	
}
