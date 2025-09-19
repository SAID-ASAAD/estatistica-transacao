package com.said.estatistica_transacao.services;

import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import com.said.estatistica_transacao.infrastructure.repositories.TransacaoRepository;
import com.said.estatistica_transacao.services.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    @DisplayName("Deve receber uma transação válida e chamar o Repository")
    void receberTransacaoComSucesso() {
        TransacaoDto transacaoDto = new TransacaoDto(100.0, OffsetDateTime.now());

        transacaoService.receberTransacao(transacaoDto);

        verify(transacaoRepository, times(1)).receberTransacao(transacaoDto);
    }

    @Test
    @DisplayName("Deve lançar exceção ao receber transação com valor negativo")
    void receberTransacaoComValorNegativo() {
        TransacaoDto transacaoComValorNegativo = new TransacaoDto(-10.0, OffsetDateTime.now());

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.receberTransacao(transacaoComValorNegativo));

        assertTrue(exception.getMessage().contains("valor negativo"));
        verify(transacaoRepository, never()).receberTransacao(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao receber transação com data futura")
    void receberTransacaoComDataFutura() {
        TransacaoDto transacaoComDataFutura = new TransacaoDto(100.0, OffsetDateTime.now().plusDays(1));

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.receberTransacao(transacaoComDataFutura));

        assertTrue(exception.getMessage().contains("Transação recusada por estar cadastrada no futuro"));
        verify(transacaoRepository, never()).receberTransacao(any());
    }

    @Test
    @DisplayName("Deve chamar o método de busca do Repository e retornar a lista")
    void buscarTransacaoComSucesso() {
        int intervaloBusca = 60;
        List<TransacaoDto> transacoesMock = List.of(new TransacaoDto(100.0, OffsetDateTime.now()));
        when(transacaoRepository.buscarTransacao(intervaloBusca)).thenReturn(transacoesMock);

        List<TransacaoDto> result = transacaoService.buscarTransacao(intervaloBusca);

        verify(transacaoRepository, times(1)).buscarTransacao(intervaloBusca);
        assertEquals(transacoesMock, result);
    }

    @Test
    @DisplayName("Deve chamar o método de deleção do Repository")
    void deletarTransacaoComSucesso() {
        transacaoService.deletarTransacao();

        verify(transacaoRepository, times(1)).deletarTransacao();
    }
}
