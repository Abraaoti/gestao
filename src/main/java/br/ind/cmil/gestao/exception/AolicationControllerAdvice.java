package br.ind.cmil.gestao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@RestControllerAdvice
public class AolicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String HanderNotFoundException(RecordNotFoundException ex) {
        return ex.getMessage();
    }

}
