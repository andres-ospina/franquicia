package com.accenture.franquicia.util.controller;


import com.accenture.franquicia.util.constant.ConstantMessage;
import com.accenture.franquicia.util.controller.exception.DeleteException;
import com.accenture.franquicia.util.controller.exception.ValidationException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public Mono<ResponseEntity<GeneralResponseMessage>> handlerValidationException(ValidationException ex){
       GeneralResponseMessage response = new GeneralResponseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
       return Mono.just(response)
               .map(responseException -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseException));
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    public Mono<ResponseEntity<GeneralResponseMessage>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        log.error(ex.getMessage(), ex);
        GeneralResponseMessage response =  new GeneralResponseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),  ConstantMessage.ERROR);
        return Mono.just(response)
                .map(responseException -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseException));

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public  Mono<ResponseEntity<GeneralResponseMessage>> handleConstraintViolationException(
            ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        GeneralResponseMessage response =  new GeneralResponseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),  ConstantMessage.ERROR);
        return Mono.just(response)
                .map(responseException -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseException));


    }

    @ExceptionHandler(DeleteException.class)
    public Mono<ResponseEntity<GeneralResponseMessage>> handleDeleteException(
            DeleteException ex) {
        log.error(ex.getMessage(), ex);
        GeneralResponseMessage response =  new GeneralResponseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return Mono.just(response)
                .map(responseException -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseException));

    }

}
