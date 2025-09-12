package com.said.estatistica_transacao.controllers;

import com.said.estatistica_transacao.infrastructure.dtos.EstatisticaResponse;
import com.said.estatistica_transacao.services.EstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Calcula as estatísticas das transações registradas")
    @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso")
    public ResponseEntity<EstatisticaResponse> obterEstatisticas(@RequestParam(
            value = "intervaloBusca", required = false, defaultValue = "60") int intervaloBusca){

        var estatisticas = estatisticaService.obterEstatisticas(intervaloBusca);
        return ResponseEntity.ok(estatisticas);
    }
}
