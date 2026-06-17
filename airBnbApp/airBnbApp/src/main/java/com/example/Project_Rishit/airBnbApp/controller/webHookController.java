package com.example.Project_Rishit.airBnbApp.controller;

import com.example.Project_Rishit.airBnbApp.service.BookingService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webhook")
@Slf4j
public class webHookController {
     private  final BookingService bookingService;


     @Value("${stripe.webhook.secret}")
    private String endPointSecret;

     @PostMapping("/payment")
    public ResponseEntity<Void> capturePayments(@RequestBody String Payload,@RequestHeader("Stripe-Signature") String sigheader){

         try{
             Event event = Webhook.constructEvent(Payload,sigheader,endPointSecret);
             bookingService.capturePayment(event);
             return ResponseEntity.noContent().build();

         }
         catch (SignatureVerificationException e){
             throw new RuntimeException(e);
         }

     }

}
