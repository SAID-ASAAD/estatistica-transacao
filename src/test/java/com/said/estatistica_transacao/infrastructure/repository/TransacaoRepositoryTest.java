package com.said.estatistica_transacao.infrastructure.repository;

import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import com.said.estatistica_transacao.infrastructure.repositories.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransacaoRepositoryTest {

    private TransacaoRepository transacaoRepository;

    @BeforeEach
    void setup(){
        transacaoRepository = new TransacaoRepository();
    }

    @Test
    @DisplayName("Deve receber e armazenar transação com sucesso")
    void receberTransacaoComSucesso(){
        TransacaoDto transacaoDto = new TransacaoDto(100.0, OffsetDateTime.now());

        transacaoRepository.receberTransacao(transacaoDto);
        List<TransacaoDto> transacoes = transacaoRepository.buscarTransacao(60);

        assertTrue(transacoes.contains(transacaoDto));
    }

    @Test
    @DisplayName("Deve limpar a lista de transações com sucesso")
    void deletarTransacaoComSucesso() {
        transacaoRepository.receberTransacao(new TransacaoDto(100.0, OffsetDateTime.now()));

        transacaoRepository.deletarTransacao();
        List<TransacaoDto> transacoes = transacaoRepository.buscarTransacao(60);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    @DisplayName("Deve buscar apenas transações dentro do intervalo de tempo especificado")
    void buscarTransacaoComSucesso() {
        TransacaoDto transacaoRecente = new TransacaoDto(100.0, OffsetDateTime.now().minusSeconds(30));
        TransacaoDto transacaoAntiga = new TransacaoDto(200.0, OffsetDateTime.now().minusSeconds(90));
        transacaoRepository.receberTransacao(transacaoRecente);
        transacaoRepository.receberTransacao(transacaoAntiga);

        List<TransacaoDto> transacoes = transacaoRepository.buscarTransacao(60);

        assertEquals(1, transacoes.size());
        assertTrue(transacoes.contains(transacaoRecente));
    }
}
