package com.example.bookingtrain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseObject<T> extends ResponseEntity<ResponseObject.Payload<T>> {

    // Constructor để tạo ResponseEntity với Payload
    public ResponseObject(HttpStatus code, String message, T data) {
        super(new Payload<>(code.value(), message, data), code);
    }

    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL) // To exclude null values in the response
    public static class Payload<T> {
        public int code;
        public String message;
        public T data;
    }
}