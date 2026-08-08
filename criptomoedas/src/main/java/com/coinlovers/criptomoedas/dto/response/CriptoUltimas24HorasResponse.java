package com.coinlovers.criptomoedas.dto.response;

import lombok.Builder;

@Builder
public record CriptoUltimas24HorasResponse(String criptoMoeda,
		Double ultimoPreco,
		Double porcentagemMudancaPreco,
		Double volume) {

}
