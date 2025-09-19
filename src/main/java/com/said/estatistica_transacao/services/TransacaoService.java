package com.said.estatistica_transacao.services;

import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import com.said.estatistica_transacao.infrastructure.repositories.TransacaoRepository;
import com.said.estatistica_transacao.services.exceptions.UnprocessableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransacaoService {

    private final Logger log = LoggerFactory.getLogger(TransacaoService.class);
    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }

    public void receberTransacao(TransacaoDto transacao) {
        long inicio = System.currentTimeMillis();
        log.info("Iniciado o processamento de registrar transações {}", transacao);

        if (transacao.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora cadastradas no futuro");
            throw new UnprocessableEntity("Transação recusada por estar cadastrada no futuro");
        }
        if (transacao.valor() < 0) {
            log.error("Transação com valor negativo");
            throw new UnprocessableEntity("Transação recusada por ter valor negativo");
        }

        repository.receberTransacao(transacao);
        log.info("Transação registrada com sucesso");
        long fim = System.currentTimeMillis();
        long tempoRequisicao = fim - inicio;
        System.out.println("Tempo gasto pela requisição (ms): " + tempoRequisicao);
    }

    public void deletarTransacao() {
        long inicio = System.currentTimeMillis();

        log.info("Iniciado processamento para deletar transações");

        repository.deletarTransacao();

        log.info("Transações deletadas com sucesso");

        long fim = System.currentTimeMillis();
        long tempoRequisicao = fim - inicio;
        System.out.println("Tempo gasto pela requisição (ms): " + tempoRequisicao);
    }

    public List<TransacaoDto> buscarTransacao(int intervaloBusca) {
        long inicio = System.currentTimeMillis();

        log.info("Inicado processo de busca de transações nos últimos {} segundos", intervaloBusca);

        log.info("Retorno de transações em andamento");

        long fim = System.currentTimeMillis();
        long tempoRequisicao = fim - inicio;
        System.out.println("Tempo gasto pela requisição (ms): " + tempoRequisicao);

        return repository.buscarTransacao(intervaloBusca);
    }

}
