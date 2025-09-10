package com.said.estatistica_transacao.infrastructure.dtos;

import java.time.OffsetDateTime;

public record TransacaoRequestDto(Double valor, OffsetDateTime dataHora) {
}
