package com.said.estatistica_transacao.controllers.exceptions;

import com.said.estatistica_transacao.services.exceptions.UnprocessableEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<StandardError> unprocessableEntity(UnprocessableEntity e, HttpServletRequest request){
        String error = "Dados inválidos";
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError sError = new StandardError(OffsetDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(sError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> badRequest(HttpServletRequest request){
        String error = "Requisição com erro de formatação";
        String message = "Certifique-se que os campos (valor e dataHora) foram preenchidos corretamente -> dataHora deve estar no padrão ISO 8601 e o valor deve atender ao formato do tipo Double";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError sError = new StandardError(OffsetDateTime.now(), status.value(), error, message, request.getRequestURI());
        log.error("Erro de formatação na requisição");
        return ResponseEntity.status(status).body(sError);
    }

}
