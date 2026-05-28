package com.example.Project_Rishit.airBnbApp.advices;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;


    @RestControllerAdvice
    public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            return false;
        }

        @Override
        public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
            if(body instanceof ApiResponse<?>){
                return  body;
            }
            return new ApiResponse<>(body);
        }

        @Override
        public @Nullable Map<String, Object> determineWriteHints(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType) {
            return ResponseBodyAdvice.super.determineWriteHints(body, returnType, selectedContentType, selectedConverterType);
        }
    }


