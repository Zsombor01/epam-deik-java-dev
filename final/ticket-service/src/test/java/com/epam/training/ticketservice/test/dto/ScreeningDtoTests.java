package com.epam.training.ticketservice.test.dto;

import com.epam.training.ticketservice.dto.ScreeningDto;
import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.model.Screening;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScreeningDtoTest {

    @Test
    void testConstructorMapsScreeningFieldsCorrectly() {
        Movie mockMovie = mock(Movie.class);
        when(mockMovie.getTitle()).thenReturn("Inception");
        when(mockMovie.getCategory()).thenReturn("Sci-Fi");
        when(mockMovie.getLength()).thenReturn(148);

        Room mockRoom = mock(Room.class);
        when(mockRoom.getName()).thenReturn("Main Hall");

        LocalDateTime mockStartTime = LocalDateTime.of(2024, 1, 15, 20, 30);

        Screening mockScreening = mock(Screening.class);
        when(mockScreening.getMovie()).thenReturn(mockMovie);
        when(mockScreening.getRoom()).thenReturn(mockRoom);
        when(mockScreening.getStartTime()).thenReturn(mockStartTime);

        ScreeningDto screeningDto = new ScreeningDto(mockScreening);

        assertNotNull(screeningDto.getMovie());
        assertEquals("Inception", screeningDto.getMovie().getTitle());
        assertEquals("Main Hall", screeningDto.getRoomName());
        assertEquals("2024-01-15 20:30", screeningDto.getStartTime());
    }

    @Test
    void testToStringFormatIsCorrect() {
        Movie mockMovie = mock(Movie.class);
        when(mockMovie.getTitle()).thenReturn("The Matrix");
        when(mockMovie.getCategory()).thenReturn("Action");
        when(mockMovie.getLength()).thenReturn(136);

        Room mockRoom = mock(Room.class);
        when(mockRoom.getName()).thenReturn("Sci-Fi Hall");

        LocalDateTime mockStartTime = LocalDateTime.of(2024, 2, 20, 19, 45);

        Screening mockScreening = mock(Screening.class);
        when(mockScreening.getMovie()).thenReturn(mockMovie);
        when(mockScreening.getRoom()).thenReturn(mockRoom);
        when(mockScreening.getStartTime()).thenReturn(mockStartTime);

        ScreeningDto screeningDto = new ScreeningDto(mockScreening);

        assertEquals("The Matrix (Action, 136 minutes), screened in room Sci-Fi Hall, at 2024-02-20 19:45", screeningDto.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Movie movie1 = mock(Movie.class);
        when(movie1.getTitle()).thenReturn("Inception");
        when(movie1.getCategory()).thenReturn("Sci-Fi");
        when(movie1.getLength()).thenReturn(148);

        Room room1 = mock(Room.class);
        when(room1.getName()).thenReturn("Main Hall");

        LocalDateTime startTime1 = LocalDateTime.of(2024, 1, 15, 20, 30);

        Screening screening1 = mock(Screening.class);
        when(screening1.getMovie()).thenReturn(movie1);
        when(screening1.getRoom()).thenReturn(room1);
        when(screening1.getStartTime()).thenReturn(startTime1);

        Movie movie2 = mock(Movie.class);
        when(movie2.getTitle()).thenReturn("Inception");
        when(movie2.getCategory()).thenReturn("Sci-Fi");
        when(movie2.getLength()).thenReturn(148);

        Room room2 = mock(Room.class);
        when(room2.getName()).thenReturn("Main Hall");

        LocalDateTime startTime2 = LocalDateTime.of(2024, 1, 15, 20, 30);

        Screening screening2 = mock(Screening.class);
        when(screening2.getMovie()).thenReturn(movie2);
        when(screening2.getRoom()).thenReturn(room2);
        when(screening2.getStartTime()).thenReturn(startTime2);

        Movie differentMovie = mock(Movie.class);
        when(differentMovie.getTitle()).thenReturn("Interstellar");
        when(differentMovie.getCategory()).thenReturn("Sci-Fi");
        when(differentMovie.getLength()).thenReturn(169);

        Room differentRoom = mock(Room.class);
        when(differentRoom.getName()).thenReturn("Sci-Fi Hall");

        LocalDateTime differentStartTime = LocalDateTime.of(2024, 2, 20, 19, 45);

        Screening differentScreening = mock(Screening.class);
        when(differentScreening.getMovie()).thenReturn(differentMovie);
        when(differentScreening.getRoom()).thenReturn(differentRoom);
        when(differentScreening.getStartTime()).thenReturn(differentStartTime);

        ScreeningDto dto1 = new ScreeningDto(screening1);
        ScreeningDto dto2 = new ScreeningDto(screening2);
        ScreeningDto differentDto = new ScreeningDto(differentScreening);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, differentDto);

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), differentDto.hashCode());
    }

    @Test
    void testGetters() {
        Movie mockMovie = mock(Movie.class);
        when(mockMovie.getTitle()).thenReturn("Star Wars");
        when(mockMovie.getCategory()).thenReturn("Science Fiction");
        when(mockMovie.getLength()).thenReturn(121);

        Room mockRoom = mock(Room.class);
        when(mockRoom.getName()).thenReturn("Galaxy Hall");

        LocalDateTime mockStartTime = LocalDateTime.of(2024, 3, 10, 18, 15);

        Screening mockScreening = mock(Screening.class);
        when(mockScreening.getMovie()).thenReturn(mockMovie);
        when(mockScreening.getRoom()).thenReturn(mockRoom);
        when(mockScreening.getStartTime()).thenReturn(mockStartTime);

        ScreeningDto screeningDto = new ScreeningDto(mockScreening);

        assertNotNull(screeningDto.getMovie());
        assertEquals("Star Wars", screeningDto.getMovie().getTitle());
        assertEquals("Galaxy Hall", screeningDto.getRoomName());
        assertEquals("2024-03-10 18:15", screeningDto.getStartTime());
    }
}
