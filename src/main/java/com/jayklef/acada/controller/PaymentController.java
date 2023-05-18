package com.jayklef.acada.controller;

import com.jayklef.acada.entity.CreatePayment;
import com.jayklef.acada.entity.CreatePaymentResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse CreatePaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("NGN")
                .setAmount(25 * 100L) // what product does user wants to buy and at what cost
                .build();

        //create paymentIntent with the order amount and currency
        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());

    }
}
