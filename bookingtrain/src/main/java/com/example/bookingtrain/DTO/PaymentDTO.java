package com.example.bookingtrain.DTO;

import lombok.Builder;
import lombok.Getter;

public abstract class PaymentDTO {

    @Builder
    @Getter
    public static class VNPayResponse {
        private String code;
        private String message;
        private String paymentUrl;

        // Explicitly declare the constructor as public
        public VNPayResponse(String code, String message, String paymentUrl) {
            this.code = code;
            this.message = message;
            this.paymentUrl = paymentUrl;
        }
    }
}