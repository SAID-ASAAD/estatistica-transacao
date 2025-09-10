package com.said.estatistica_transacao.services;

import com.said.estatistica_transacao.infrastructure.dtos.EstatisticaResponse;
import com.said.estatistica_transacao.infrastructure.dtos.TransacaoDto;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;

@Service
public class EstatisticaService {

    private final TransacaoService transacaoService;

    public EstatisticaService(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    public EstatisticaResponse obterEstatisticas(int intervaloBusca){
       DoubleSummaryStatistics estatisticas  = transacaoService.buscarTransacao(intervaloBusca).stream()
                .mapToDouble(TransacaoDto::valor).summaryStatistics();
       return new EstatisticaResponse(estatisticas);
    }
}
