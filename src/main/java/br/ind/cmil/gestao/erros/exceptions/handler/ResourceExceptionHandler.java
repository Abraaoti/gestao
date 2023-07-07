package br.ind.cmil.gestao.erros.exceptions.handler;

import br.ind.cmil.gestao.exceptions.AcessoNegadoException;
import br.ind.cmil.gestao.exceptions.FuncionarioException;
import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.exceptions.PerfilExistenteException;
import br.ind.cmil.gestao.exceptions.dto.StandardError;
import br.ind.cmil.gestao.exceptions.TokenException;
import br.ind.cmil.gestao.exceptions.UsuarioExistenteException;
import br.ind.cmil.gestao.exceptions.UsuarioNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author abraao
 */

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ObjectNotFoundException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Objecto não enctrado!",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(PerfilExistenteException.class)
    public ResponseEntity<StandardError> entityNotFoundPerfil(PerfilExistenteException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Objecto não enctrado!",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<StandardError> entityNotFoundToken(TokenException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Objecto não enctrado!",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundUsuario(UsuarioNotFoundException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Objecto não enctrado!",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<StandardError> entityConflit(UsuarioExistenteException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(Instant.now(), HttpStatus.CONFLICT.value(), "Objecto já cadastrado enctrado!",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
    @ExceptionHandler(FuncionarioException.class)
    public ResponseEntity<StandardError> entityNotFoundFuncionario(FuncionarioException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Objecto não enctrado!",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(AcessoNegadoException.class)
    public ResponseEntity<StandardError> acessoNegado(AcessoNegadoException ex, HttpServletRequest request) {
        StandardError erro = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Acesso não permitido para este usuário!",
                ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
