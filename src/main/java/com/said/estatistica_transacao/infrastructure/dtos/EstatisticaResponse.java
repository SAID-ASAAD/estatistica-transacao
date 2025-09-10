package com.said.estatistica_transacao.infrastructure.dtos;


import java.util.DoubleSummaryStatistics;

public class EstatisticaResponse {

    private final Long count;
    private final Double sum;
    private final Double avg;
    private final Double min;
    private final Double max;

    public EstatisticaResponse(DoubleSummaryStatistics estatisticas) {
        this.count = estatisticas.getCount();
        this.sum = estatisticas.getSum();
        this.avg = estatisticas.getAverage();
        this.min = estatisticas.getMin() == Double.POSITIVE_INFINITY ? 0 : estatisticas.getMin();
        this.max = estatisticas.getMax()== Double.NEGATIVE_INFINITY ? 0 : estatisticas.getMin();
    }

    public Long getCount() {
        return count;
    }

    public Double getSum() {
        return sum;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }
}
