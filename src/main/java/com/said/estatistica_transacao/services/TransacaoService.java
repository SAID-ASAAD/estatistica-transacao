package com.said.estatistica_transacao.services;

import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import com.said.estatistica_transacao.infrastructure.repositories.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final Logger log = LoggerFactory.getLogger(TransacaoService.class);
    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository){
        this.repository = repository;
    }

    public void receberTransacao(TransacaoDto transacao){
        log.info("Iniciado o processamento de registrar transações {}", transacao);

        repository.receberTransacao(transacao);
        log.info("Transações registradas com sucesso");
    }

    public void deletarTtransacao(){
        log.info("Iniciado processamento para deletar transações");

        repository.deletarTransacao();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransacaoDto> buscarTransacao(int intervaloBusca){
        log.info("Inicado processo de busca de transações nos últimos {} segundos", intervaloBusca);

        log.info("Retorno de transações em andamento");
        return repository.buscarTransacao(intervaloBusca);
    }

}
