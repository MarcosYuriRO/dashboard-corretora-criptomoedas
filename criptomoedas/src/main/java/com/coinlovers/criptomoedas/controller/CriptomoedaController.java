package com.coinlovers.criptomoedas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coinlovers.criptomoedas.dto.response.CriptosUltimas24HorasResponse;
import com.coinlovers.criptomoedas.service.CriptomoedaService;

@RestController
@RequestMapping("/criptomoedas/24h")
public class CriptomoedaController { 
	
	private CriptomoedaService service;

	public CriptomoedaController(CriptomoedaService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<CriptosUltimas24HorasResponse>> consultaCriptomoeda(@RequestParam(required = false) String criptomoeda){
		return ResponseEntity.status(HttpStatus.OK).body(service.consultaCriptomoeda(criptomoeda));
	}
	
	@GetMapping("/valorizacao")
	public ResponseEntity<List<CriptosUltimas24HorasResponse>> consultaMaisVendidas() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultaCriptoMoedasUltimas24Horas());
	}
	
}
