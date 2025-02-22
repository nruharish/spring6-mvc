package org.nruharish.springmvc.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomErrorController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){

        List errorList = exception.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler(TransactionSystemException.class)
    ResponseEntity handleJPAErrors(TransactionSystemException transactionSystemException){
        ResponseEntity.BodyBuilder responseEntitybuilder = ResponseEntity.badRequest();
        if(transactionSystemException.getCause().getCause() instanceof ConstraintViolationException){
            ConstraintViolationException constraintViolationException = (ConstraintViolationException)transactionSystemException.getCause().getCause();
            List errors = constraintViolationException.getConstraintViolations().stream()
                    .map(
                            constraintViolation -> {
                            Map<String, String> errMap = new HashMap<>();
                            errMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                            return errMap;
                            }).collect(Collectors.toList());
            return responseEntitybuilder.body(errors);
        }
        return responseEntitybuilder.build();
    }
}
