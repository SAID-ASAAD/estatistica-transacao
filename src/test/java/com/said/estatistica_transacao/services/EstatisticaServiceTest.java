package com.said.estatistica_transacao.services;

import com.said.estatistica_transacao.infrastructure.dtos.EstatisticaResponse;
import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstatisticaServiceTest {

    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private EstatisticaService estatisticaService;

    @Test
    @DisplayName("Deve calcular as estatísticas corretamente para uma lista de transações")
    void obterEstatisticasComSucesso() {
        int intervaloBusca = 60;
        List<TransacaoDto> transacoes = List.of(
                new TransacaoDto(100.0, OffsetDateTime.now()),
                new TransacaoDto(200.0, OffsetDateTime.now()),
                new TransacaoDto(300.0, OffsetDateTime.now())
        );

        when(transacaoService.buscarTransacao(intervaloBusca)).thenReturn(transacoes);

        EstatisticaResponse estatistica = estatisticaService.obterEstatisticas(intervaloBusca);

        verify(transacaoService, times(1)).buscarTransacao(intervaloBusca);

        assertEquals(3, estatistica.getCount());
        assertEquals(600.0, estatistica.getSum());
        assertEquals(200.0, estatistica.getAvg());
        assertEquals(300.0, estatistica.getMax());
        assertEquals(100.0, estatistica.getMin());
    }

    @Test
    @DisplayName("Deve retornar estatísticas zeradas quando não houver transações")
    void obterEstatisticasComListaVazia() {
        int intervaloBusca = 60;
        Long countZero = 0L;
        Double statsZero = 0.0;

        when(transacaoService.buscarTransacao(intervaloBusca)).thenReturn(Collections.emptyList());

        EstatisticaResponse response = estatisticaService.obterEstatisticas(intervaloBusca);

        verify(transacaoService, times(1)).buscarTransacao(intervaloBusca);
        assertEquals(countZero, response.getCount());
        assertEquals(statsZero, response.getSum());
        assertEquals(statsZero, response.getAvg());
        assertEquals(statsZero, response.getMin());
        assertEquals(statsZero, response.getMax());


    }
}
