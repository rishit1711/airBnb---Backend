package com.example.Project_Rishit.airBnbApp.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(NoSuchElementException.class)
        public ResponseEntity<ApiError> handleResourceException(NoSuchElementException exception){
            ApiError apiError = ApiError.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Resource not found")
                    .build();
            return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(Exception.class)
        public  ResponseEntity<ApiError> handleAllExceptions(Exception e){
            ApiError apiError = ApiError.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();

            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

