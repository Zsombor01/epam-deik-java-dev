package com.epam.training.ticketservice.test.dto;

import com.epam.training.ticketservice.dto.MovieDto;
import com.epam.training.ticketservice.model.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieDtoTest {

    @Test
    void testConstructorMapsMovieFieldsCorrectly() {
        Movie mockMovie = mock(Movie.class);
        when(mockMovie.getTitle()).thenReturn("Inception");
        when(mockMovie.getCategory()).thenReturn("Sci-Fi");
        when(mockMovie.getLength()).thenReturn(148);

        MovieDto movieDto = new MovieDto(mockMovie);

        assertEquals("Inception", movieDto.getTitle());
        assertEquals("Sci-Fi", movieDto.getCategory());
        assertEquals(148, movieDto.getLength());
    }

    @Test
    void testToStringFormatIsCorrect() {
        Movie mockMovie = mock(Movie.class);
        when(mockMovie.getTitle()).thenReturn("The Matrix");
        when(mockMovie.getCategory()).thenReturn("Action");
        when(mockMovie.getLength()).thenReturn(136);

        MovieDto movieDto = new MovieDto(mockMovie);

        assertEquals("The Matrix (Action, 136 minutes)", movieDto.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Movie movie1 = mock(Movie.class);
        when(movie1.getTitle()).thenReturn("Inception");
        when(movie1.getCategory()).thenReturn("Sci-Fi");
        when(movie1.getLength()).thenReturn(148);

        Movie movie2 = mock(Movie.class);
        when(movie2.getTitle()).thenReturn("Inception");
        when(movie2.getCategory()).thenReturn("Sci-Fi");
        when(movie2.getLength()).thenReturn(148);

        Movie differentMovie = mock(Movie.class);
        when(differentMovie.getTitle()).thenReturn("Interstellar");
        when(differentMovie.getCategory()).thenReturn("Sci-Fi");
        when(differentMovie.getLength()).thenReturn(169);

        MovieDto dto1 = new MovieDto(movie1);
        MovieDto dto2 = new MovieDto(movie2);
        MovieDto differentDto = new MovieDto(differentMovie);

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

        MovieDto movieDto = new MovieDto(mockMovie);

        assertEquals("Star Wars", movieDto.getTitle());
        assertEquals("Science Fiction", movieDto.getCategory());
        assertEquals(121, movieDto.getLength());
    }
}
