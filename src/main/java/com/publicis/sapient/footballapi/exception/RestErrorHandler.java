package com.publicis.sapient.footballapi.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestErrorHandler {
  protected final Log logger = LogFactory.getLog(RestErrorHandler.class);

  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity handleNotFound(final NotFoundException ex, final WebRequest request) {
    logger.error("Unable to find the aggregate or entity: ", ex);
    return  ResponseEntity.notFound().build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
    final Map<String, String> errors = new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public Map<String, String> handleIllegalArgumentException(final IllegalArgumentException ex) {
    final Map<String, String> errors = new HashMap<>();

    errors.put("Authentication Failed:", "Username or Password is incorrect");

    return errors;
  }
}
