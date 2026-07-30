package com.coinlovers.criptomoedas.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coinlovers.criptomoedas.client.BinanceAPIClient;
import com.coinlovers.criptomoedas.dto.client.BinanceCriptosUltimas24HorasResponse;
import com.coinlovers.criptomoedas.dto.response.CriptosUltimas24HorasResponse;

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
	
	public List<CriptosUltimas24HorasResponse> consultaCriptomoeda(String criptoMoeda) {
		String simbolo = criptoMoeda + MOEDA_COTACAO_PADRAO;
		log.info("Consultando o sistema da Binance sobre o preço da criptomoeda {}.", simbolo);
		
		List<BinanceCriptosUltimas24HorasResponse> response = client.consultarUltimas24Horas();
		
		log.info("Consulta de preço realizada!");
		
		if (criptoMoeda == null) {
			return response.stream()
					.filter(cripto -> cripto.criptoMoeda().endsWith(MOEDA_COTACAO_PADRAO))
					.map(cripto -> CriptosUltimas24HorasResponse
							.builder()
							.criptoMoeda(cripto.criptoMoeda())
							.ultimoPreco(cripto.ultimoPreco())
							.porcentagemMudancaPreco(cripto.porcentagemMudancaPreco())
							.volume(cripto.volume())
							.build())
					.toList();
		}
		
		return response.stream()
				.filter(cripto -> cripto.criptoMoeda().contains(criptoMoeda.concat(MOEDA_COTACAO_PADRAO)))
				.map(cripto -> CriptosUltimas24HorasResponse
						.builder()
						.criptoMoeda(cripto.criptoMoeda())
						.ultimoPreco(cripto.ultimoPreco())
						.porcentagemMudancaPreco(cripto.porcentagemMudancaPreco())
						.volume(cripto.volume())
						.build())
				.toList();
		
	}
	
	public List<CriptosUltimas24HorasResponse> consultaCriptoMoedasUltimas24Horas() {
		
		
		List<BinanceCriptosUltimas24HorasResponse> response = client.consultarUltimas24Horas();
		
		return response.stream()
				.filter(cripto -> cripto.criptoMoeda().endsWith(MOEDA_COTACAO_PADRAO))
				.sorted(Comparator.comparing(BinanceCriptosUltimas24HorasResponse::porcentagemMudancaPreco).reversed())
				.map(cripto -> CriptosUltimas24HorasResponse
						.builder()
						.criptoMoeda(cripto.criptoMoeda())
						.ultimoPreco(cripto.ultimoPreco())
						.porcentagemMudancaPreco(cripto.porcentagemMudancaPreco())
						.volume(cripto.volume())
						.build())
				.limit(LIMITE_CRIPTOMOEDAS)
				.toList();
	}
	
}
