package com.coinlovers.criptomoedas.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.coinlovers.criptomoedas.dto.client.BinanceCriptosUltimas24HorasResponse;

@FeignClient(name = "binanceAPIClient", url = "https://api.binance.com/api/v3/ticker")
public interface BinanceAPIClient {
	@GetMapping("/24hr")
	List<BinanceCriptosUltimas24HorasResponse> consultarUltimas24Horas();
}
