package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.dto.BookingDto;
import com.epam.training.ticketservice.exception.OperationException;
import com.epam.training.ticketservice.util.Result;

public interface BookingService {
    Result<BookingDto, OperationException> createBooking(String movieTitle, String roomName, String startTime,
                                                         String seats);

    Result<String, OperationException> viewPricing(String movieTitle, String roomName, String startTime, String seats);
}
