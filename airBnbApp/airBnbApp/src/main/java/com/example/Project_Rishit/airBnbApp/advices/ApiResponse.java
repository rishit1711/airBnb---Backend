package com.example.Project_Rishit.airBnbApp.advices;

import lombok.Data;

import java.time.LocalDateTime;


    @Data
    public class ApiResponse<T>{
        private LocalDateTime timeStamp;
        private T data;
        private ApiError error;

        public ApiResponse(T data) {
            this.data = data;
        }

        public ApiResponse() {
            this.timeStamp = LocalDateTime.now();
        }

        public ApiResponse(ApiError error) {
            this.error = error;
        }
    }


