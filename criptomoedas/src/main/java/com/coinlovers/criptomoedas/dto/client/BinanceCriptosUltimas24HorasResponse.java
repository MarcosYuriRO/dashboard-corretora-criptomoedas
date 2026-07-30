package com.coinlovers.criptomoedas.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BinanceCriptosUltimas24HorasResponse(@JsonProperty("symbol") String criptoMoeda,
		@JsonProperty("lastPrice") Double ultimoPreco,
		@JsonProperty("priceChangePercent") Double porcentagemMudancaPreco,
		Double volume) {
	
}
