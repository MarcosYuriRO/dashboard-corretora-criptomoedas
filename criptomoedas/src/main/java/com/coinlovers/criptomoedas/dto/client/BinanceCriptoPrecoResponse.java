package com.coinlovers.criptomoedas.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BinanceCriptoPrecoResponse(@JsonProperty("symbol") String criptoMoeda,
		@JsonProperty("price") Double preco) {
}
