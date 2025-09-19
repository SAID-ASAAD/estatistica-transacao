package com.said.estatistica_transacao.controllers;

import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import com.said.estatistica_transacao.services.TransacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransacaoControllerTest {

    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private TransacaoController transacaoController;

    @Test
    @DisplayName("Deve chamar a Service para receber a transação e retornar status 201")
    void receberTransacaoComSucesso() {
        TransacaoDto transacaoDto = new TransacaoDto(100.0, OffsetDateTime.now());

        ResponseEntity<Void> response = transacaoController.receberTransacao(transacaoDto);

        verify(transacaoService, times(1)).receberTransacao(transacaoDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Deve chamar a Service para deletar as transações e retornar status 200")
    void deletarTransacaoComSucesso() {

        ResponseEntity<Void> response = transacaoController.deletarTransacao();

        verify(transacaoService, times(1)).deletarTransacao();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
