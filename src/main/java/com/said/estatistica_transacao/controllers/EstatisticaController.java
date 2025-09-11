package com.said.estatistica_transacao.controllers;

import com.said.estatistica_transacao.infrastructure.dtos.EstatisticaResponse;
import com.said.estatistica_transacao.services.EstatisticaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService estatisticaService;
    public EstatisticaController(EstatisticaService estatisticaService){
        this.estatisticaService = estatisticaService;
    }

    @GetMapping
    public ResponseEntity<EstatisticaResponse> obterEstatisticas(@RequestParam(
            value = "intervaloBusca", required = false, defaultValue = "60") int intervaloBusca){

        var estatisticas = estatisticaService.obterEstatisticas(intervaloBusca);
        return ResponseEntity.ok(estatisticas);
    }
}
