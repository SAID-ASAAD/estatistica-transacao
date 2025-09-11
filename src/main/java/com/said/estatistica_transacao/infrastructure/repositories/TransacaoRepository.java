package com.said.estatistica_transacao.infrastructure.repositories;

import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

    private final List<TransacaoDto> transacoes = new ArrayList<>();

    public void receberTransacao(TransacaoDto transacao){
        transacoes.add(transacao);
    }

    public void deletarTransacao(){
        transacoes.clear();
    }

    public List<TransacaoDto> buscarTransacao(int intervaloBusca){
        OffsetDateTime periodoBusca = OffsetDateTime.now().minusSeconds(intervaloBusca);
        return transacoes.stream()
                .filter(t -> t.dataHora().isAfter(periodoBusca))
                .toList();
    }
}
