package com.said.estatistica_transacao.services;

import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import com.said.estatistica_transacao.infrastructure.repositories.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository){
        this.repository = repository;
    }

    public void receberTransacao(TransacaoDto transacao){
        repository.receberTransacao(transacao);
    }

    public void deletarTtransacao(){
        repository.deletarTransacao();
    }

    public List<TransacaoDto> buscarTransacao(int intervaloBusca){
        return repository.buscarTransacao(intervaloBusca);
    }

}
