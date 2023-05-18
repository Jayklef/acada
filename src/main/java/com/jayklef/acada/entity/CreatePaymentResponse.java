package com.jayklef.acada.entity;

public class CreatePaymentResponse {

    private String clientSecret;

    public CreatePaymentResponse(String clientSecret){
        this.clientSecret = clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
