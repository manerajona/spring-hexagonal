package org.alkemy.ong.config.exception.handler;

import org.alkemy.ong.config.exception.NotFoundException;
import org.alkemy.ong.config.exception.error.ErrorCode;
import org.alkemy.ong.config.exception.error.ErrorLocation;
import org.alkemy.ong.ports.input.rs.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorDetails> handleNotFound(NotFoundException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .detail("The resource with id %s is not found".formatted(ex.getResourceId()))
                .location(ErrorLocation.PATH)
                .value(ex.getResourceId())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
