package com.example.bookingtrain.DTO;

import lombok.Builder;

public abstract class PaymentDTO {

    @Builder
    public static class VNPayResponse {
        public String code;
        public String message;
        public String paymentUrl;

        // Explicitly declare the constructor as public
        public VNPayResponse(String code, String message, String paymentUrl) {
            this.code = code;
            this.message = message;
            this.paymentUrl = paymentUrl;
        }
    }
}