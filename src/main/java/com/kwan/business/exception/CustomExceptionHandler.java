package com.kwan.business.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CustomErrorResponse handleException(CustomException e, HttpServletRequest request) {
        log.error("\nerrorCode:: {}\n, url:: {}\n, message:: {}\n"
                , e.getCustomErrorCode(), request.getRequestURI(), e.getMessage());

        return CustomErrorResponse.builder()
                .status(e.getCustomErrorCode())
                .statusMessage(e.getMessage())
                .build();
    }
}
