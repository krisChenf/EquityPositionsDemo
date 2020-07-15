package com.equity.position.domain.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenfei on 2020/7/12.
 */


@RestControllerAdvice
public class GlobalDefultExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleValidationBodyException(MethodArgumentNotValidException e) {
        for (ObjectError s : e.getBindingResult().getAllErrors()) {
            return new ErrorMessage(HttpStatus.BAD_REQUEST.value()+"", s.getObjectName() + ": " + s.getDefaultMessage());
        }
        return new ErrorMessage("invalid input data", "未知参数错误");
    }
    @ExceptionHandler(JsonProcessingException.class)
    public ErrorMessage handleJsonProcessingException(JsonProcessingException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value()+"",e.getMessage());
    }
    @ExceptionHandler(RuleException.class)
    public ErrorMessage handleUnProccessableServiceException(RuleException e, HttpServletResponse response) {
        response.setStatus(Integer.valueOf(e.getEnumError().getCode()));
        return new ErrorMessage(e.getEnumError().getCode(), e.getMessage());
    }
    @ExceptionHandler(org.springframework.validation.BindException.class)
    public ErrorMessage handleUnProccessableBindException(BindException e, HttpServletResponse response) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value()+"","invalid input data");
    }


}
