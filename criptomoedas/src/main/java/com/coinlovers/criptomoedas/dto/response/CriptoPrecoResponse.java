package com.coinlovers.criptomoedas.dto.response;

import lombok.Builder;

@Builder
public record CriptoPrecoResponse(String criptoMoeda, Double preco) {
}
