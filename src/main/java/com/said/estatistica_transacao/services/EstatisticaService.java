package com.said.estatistica_transacao.services;

import com.said.estatistica_transacao.infrastructure.dtos.EstatisticaResponse;
import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;

@Service
public class EstatisticaService {

    private final Logger log = LoggerFactory.getLogger(EstatisticaService.class);
    private final TransacaoService transacaoService;

    public EstatisticaService(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    public EstatisticaResponse obterEstatisticas(int intervaloBusca){
        log.info("Iniciada busca de estatísticas de transações pelo período de {} segundos", intervaloBusca);

       DoubleSummaryStatistics estatisticas  = transacaoService.buscarTransacao(intervaloBusca).stream()
                .mapToDouble(TransacaoDto::valor).summaryStatistics();

       log.info("Retorno de estatisticas em andamento");
       return new EstatisticaResponse(estatisticas);
    }
}
