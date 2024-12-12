package com.epam.training.ticketservice.dto;

import com.epam.training.ticketservice.model.Booking;
import com.epam.training.ticketservice.model.Seat;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class BookingDto {
    private final ScreeningDto screening;
    private final List<Seat> seats;

    public BookingDto(Booking dao) {
        this.screening = new ScreeningDto(dao.getScreening());
        this.seats = Seat.fromString(dao.getSeats());
    }

    @Override
    public String toString() {
        return "Seats " + String.join(", ", seats.stream().map(Seat::toString).toList())
                + " on " + screening.getMovie().getTitle()
                + " in room " + screening.getRoomName()
                + " starting at " + screening.getStartTime();
    }
}
