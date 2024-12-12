package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.dto.BookingDto;
import com.epam.training.ticketservice.model.Seat;
import com.epam.training.ticketservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class BookingCommands extends PrivilegedCommands {
    private final BookingService service;

    @ShellMethodAvailability("isSignedIn")
    @ShellMethod(key = "book", value = "Usage: <movie title> <room name> <start date> <seats>")
    public String createBooking(String movie, String room, String start, String seats) {
        var res = service.createBooking(movie, room, start, seats);
        return switch (res.state()) {
            case OK -> formatBookingDto(res.result());
            case ERROR -> res.error().getMessage();
        };
    }

    private String formatBookingDto(BookingDto dto) {
        return "Seats booked: " + String.join(", ", dto.getSeats().stream().map(Seat::toString).toList())
                + ";";
    }
}
