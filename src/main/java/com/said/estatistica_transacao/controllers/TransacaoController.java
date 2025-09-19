package com.said.estatistica_transacao.controllers;

import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import com.said.estatistica_transacao.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;
    public TransacaoController(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @PostMapping
    @Operation(description = "Recebe e armazena as transações")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Transação salva com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Requisição com erro de formatação")
    })
    public ResponseEntity<Void> receberTransacao(@RequestBody TransacaoDto transacaoDto){
        transacaoService.receberTransacao(transacaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Deleta as transações que estão armazenadas")
    @ApiResponse(responseCode = "200", description = "Transação deletada com sucesso")
    public ResponseEntity<Void> deletarTransacao(){
        transacaoService.deletarTransacao();
        return ResponseEntity.ok().build();
    }
}
