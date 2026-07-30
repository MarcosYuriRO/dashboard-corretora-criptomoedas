package com.coinlovers.criptomoedas.dto.response;

import lombok.Builder;

@Builder
public record CriptosUltimas24HorasResponse(String criptoMoeda,
		Double ultimoPreco,
		Double porcentagemMudancaPreco,
		Double volume) {

}
