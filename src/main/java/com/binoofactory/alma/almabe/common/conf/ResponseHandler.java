package com.binoofactory.alma.almabe.common.conf;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ResponseHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request) {
        Map<String, Object> exceptionResponse = new HashMap<>();

        exceptionResponse.put("result", false);
        exceptionResponse.put("time", new Date());
        exceptionResponse.put("msg", e.getMessage());
        exceptionResponse.put("details", request.getDescription(false));

        e.getStackTrace();

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public final ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException e) {
        Map<String, Object> exceptionResponse = new HashMap<>();
        exceptionResponse.put("result", false);
        exceptionResponse.put("time", new Date());
        exceptionResponse.put("code", e.getStatusCode());
        exceptionResponse.put("msg", e.getMessage());

        e.getStackTrace();

        return new ResponseEntity(exceptionResponse, e.getStatusCode());
    }

}