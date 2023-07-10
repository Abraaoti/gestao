package br.ind.cmil.gestao.erros.exceptions.handler;

import br.ind.cmil.gestao.exceptions.dto.FieldMessage;
import br.ind.cmil.gestao.exceptions.dto.StandardError;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abraao
 */
public class ValidationError {

    private final StandardError er;
    private final List<FieldMessage> messages = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
        er = new StandardError(timestamp, status, error, message, path);
    }

}
