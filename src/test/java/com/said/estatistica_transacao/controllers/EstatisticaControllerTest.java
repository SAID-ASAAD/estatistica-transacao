package com.said.estatistica_transacao.controllers;

import com.said.estatistica_transacao.infrastructure.dtos.EstatisticaResponse;
import com.said.estatistica_transacao.services.EstatisticaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.DoubleSummaryStatistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstatisticaControllerTest {

    @Mock
    private EstatisticaService estatisticaService;

    @InjectMocks
    private EstatisticaController estatisticaController;

    @Test
    @DisplayName("Deve chamar a Service para obter as estatísticas e retornar status 200 com os dados")
    void obterEstatisticasComSucesso() {
        int intervaloBusca = 60;
        DoubleSummaryStatistics estaisticas = new DoubleSummaryStatistics(5L, 100.0, 500.0, 1500.0);
        EstatisticaResponse estatisticaResponse = new EstatisticaResponse(estaisticas);

        when(estatisticaService.obterEstatisticas(intervaloBusca)).thenReturn(estatisticaResponse);

        ResponseEntity<EstatisticaResponse> response = estatisticaController.obterEstatisticas(intervaloBusca);

        verify(estatisticaService, times(1)).obterEstatisticas(intervaloBusca);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estatisticaResponse, response.getBody());
    }
}
