package com.epam.training.ticketservice.test.dto;

import com.epam.training.ticketservice.dto.BookingDto;
import com.epam.training.ticketservice.model.Booking;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.model.Seat;
import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookingDtoTest {

    private Booking booking;
    private Screening screening;

    @BeforeEach
    void setup() {
        Movie movie = new Movie("Inception", "Sci-fi", 148);
        Room room = new Room("Room1", 100, 100);
        screening = new Screening(movie, room, LocalDateTime.of(2024, 12, 15, 18, 0));

        booking = mock(Booking.class);
        when(booking.getScreening()).thenReturn(screening);
        when(booking.getSeats()).thenReturn("4,5 5,6");
    }

    @Test
    void testBookingDtoConstructor() {
        BookingDto bookingDto = new BookingDto(booking);

        assertThat(bookingDto.getScreening().getMovie().getTitle()).isEqualTo("Inception");
        assertThat(bookingDto.getScreening().getRoomName()).isEqualTo("Room1");
        assertThat(bookingDto.getSeats()).containsExactlyInAnyOrder(new Seat(4, 5), new Seat(5, 6));
    }

    @Test
    void testToString() {
        BookingDto bookingDto = new BookingDto(booking);

        String result = bookingDto.toString();

        String expected = "Seats (4,5), (5,6) on Inception in room Room1 starting at 2024-12-15 18:00";
        assertThat(result).isEqualTo(expected);
    }
}
