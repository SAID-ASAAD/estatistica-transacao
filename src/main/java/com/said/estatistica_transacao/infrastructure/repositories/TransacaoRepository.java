package com.said.estatistica_transacao.infrastructure.repositories;

import com.said.estatistica_transacao.infrastructure.dtos.TransacaoRequestDto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransacaoRepository {

    private final List<TransacaoRequestDto> transacoes = new ArrayList<>();

    public void receberTransacao(TransacaoRequestDto transacao){
        transacoes.add(transacao);
    }

    public void deletarTransacoes(){
        transacoes.clear();
    }

    public List<TransacaoRequestDto> buscarTransacoes(int intervaloBusca){
        OffsetDateTime periodoBusca = OffsetDateTime.now().minusSeconds(intervaloBusca);
        return transacoes.stream()
                .filter(t -> t.dataHora().isAfter(periodoBusca))
                .toList();
    }
}
