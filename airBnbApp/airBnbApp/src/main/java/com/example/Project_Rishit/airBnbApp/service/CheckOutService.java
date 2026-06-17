package com.example.Project_Rishit.airBnbApp.service;

import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public interface CheckOutService {

    String getCheckOutSession(Long bookingId, String successUrl, String cancelUrl) throws StripeException;
}
