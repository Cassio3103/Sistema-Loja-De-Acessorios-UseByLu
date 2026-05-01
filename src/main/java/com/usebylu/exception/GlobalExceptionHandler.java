package com.usebylu.exception;

import com.usebylu.model.Usuario;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioDuplicadoException.class)
        private ResponseEntity<RestErrorMessage> usuarioDuplicadoHandler(UsuarioDuplicadoException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT,
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
        private ResponseEntity<RestErrorMessage> usuarioNaoEncontradoHandler(UsuarioNaoEncontradoException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(NoChangeException.class)
        private ResponseEntity<RestErrorMessage> noChanceException(NoChangeException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(EstadoInvalidoException.class)
        private ResponseEntity<RestErrorMessage> estadoInvalidoException(EstadoInvalidoException exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

}
