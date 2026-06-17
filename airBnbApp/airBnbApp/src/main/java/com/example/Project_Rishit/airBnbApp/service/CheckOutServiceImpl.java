package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.Exceptions.ResourceNotFoundException;
import com.example.Project_Rishit.airBnbApp.entity.Booking;
import com.example.Project_Rishit.airBnbApp.entity.User;
import com.example.Project_Rishit.airBnbApp.repository.BookingRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class CheckOutServiceImpl implements CheckOutService{
    private final BookingRepository bookingRepository;
    @Override
    public String getCheckOutSession(Long bookingId, String successUrl, String cancelUrl) throws StripeException {
        log.info("Fetch Current Logged in User from Security Context");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Booking booking= bookingRepository.findById(bookingId).orElseThrow(()-> new ResourceNotFoundException("Booking does not exist with booking Id : "+bookingId));
        log.info("Creating the Customer who will Make the Payment ");

        Customer customer = Customer.create(
                CustomerCreateParams.builder()
                        .setName(user.getName())
                        .setEmail(user.getEmail())
                        .build()
        );


        log.info("Params Inititalized -> Contains Info in an object that will be used by stripe for Checkout ");
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCustomer(customer.getId())
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("inr")
                                                .setUnitAmount(booking.getAmount().longValue() * 100)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(booking.getHotel().getName() + " : " + booking.getRoom().getType())
                                                                .setDescription("Please Pay the Amount")
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();
        Session session = Session.create(params);

        booking.setStripeSessionId(session.getId());
        log.info("Set Session Id in the booking ");
        bookingRepository.save(booking);

        return session.getUrl();
    }
}
