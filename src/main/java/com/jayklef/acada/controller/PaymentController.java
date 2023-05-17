package com.jayklef.acada.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @PostMapping("/create-payment-intent")
    public void CreatePaymentIntent(){
        CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder
                .setCurrency("NGN")
                .setAmount(new Long(CalculateOrederAmoun(postBody.getItems)))
                .build();

        //create paymentIntent with the order amount and currency
        PaymentIntent intent = PaymentIntent.create(createParams);
        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());
        return gson.toJson(paymentResponse);
    }
}
