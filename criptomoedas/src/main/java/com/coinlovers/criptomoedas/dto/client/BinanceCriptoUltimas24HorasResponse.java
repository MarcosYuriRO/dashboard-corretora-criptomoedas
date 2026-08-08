package com.coinlovers.criptomoedas.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BinanceCriptoUltimas24HorasResponse(@JsonProperty("symbol") String criptomoeda,
		@JsonProperty("lastPrice") Double ultimoPreco,
		@JsonProperty("priceChangePercent") Double porcentagemMudancaPreco,
		Double volume) {
	
}
