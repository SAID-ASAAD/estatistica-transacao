package com.said.estatistica_transacao.infrastructure.dtos;

import java.time.OffsetDateTime;

public record TransacaoDto(Double valor, OffsetDateTime dataHora) {
}
