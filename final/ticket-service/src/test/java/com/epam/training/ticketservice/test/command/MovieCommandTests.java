package com.epam.training.ticketservice.test.command;

import com.epam.training.ticketservice.command.MovieCommands;
import com.epam.training.ticketservice.exception.OperationException;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.util.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class MovieCommandsTest {

    @Mock
    private MovieService service;

    @InjectMocks
    private MovieCommands movieCommands;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMovieSuccessfully() {
        when(service.createMovie("Title", "Category", 120))
                .thenAnswer(invocation -> Result.ok(null));

        String result = movieCommands.createMovie("Title", "Category", 120);

        assertThat(result).isEqualTo("Successfully created movie");
    }

    @Test
    void testCreateMovieFails() {
        when(service.createMovie("Title", "Category", 120))
                .thenAnswer(invocation -> Result.err(new OperationException("Error")));

        String result = movieCommands.createMovie("Title", "Category", 120);

        assertThat(result).isEqualTo("Failed to create movie: Error");
    }


    @Test
    void testUpdateMovieSuccessfully() {
        when(service.updateMovie("Title", "Category", 120))
                .thenAnswer(invocation -> Result.ok(null));

        String result = movieCommands.updateMovie("Title", "Category", 120);

        assertThat(result).isEqualTo("Successfully updated movie");
    }

    @Test
    void testUpdateMovieFails() {
        when(service.updateMovie("Title", "Category", 120))
                .thenAnswer(invocation -> Result.err(new OperationException("Error")));

        String result = movieCommands.updateMovie("Title", "Category", 120);

        assertThat(result).isEqualTo("Failed to update movie: Error");
    }

    @Test
    void testDeleteMovieSuccessfully() {
        when(service.deleteMovie("Title"))
                .thenAnswer(invocation -> Result.ok(null));

        String result = movieCommands.deleteMovie("Title");

        assertThat(result).isEqualTo("Successfully deleted movie");
    }

    @Test
    void testDeleteMovieFails() {
        when(service.deleteMovie("Title"))
                .thenAnswer(invocation -> Result.err(new OperationException("Error")));

        String result = movieCommands.deleteMovie("Title");

        assertThat(result).isEqualTo("Failed to delete movie: Error");
    }

    @Test
    void testListMoviesWhenNoMovies() {
        when(service.listMovies())
                .thenAnswer(invocation -> Result.ok(List.of()));

        String result = movieCommands.listMovies();

        assertThat(result).isEqualTo("There are no movies at the moment");
    }

    @Test
    void testListMoviesWithMovies() {
        List<String> movies = List.of("Movie1", "Movie2");
        when(service.listMovies()).thenAnswer(invocation -> Result.ok(movies));

        String result = movieCommands.listMovies();

        assertThat(result).isEqualTo("Movie1\nMovie2");
    }


    @Test
    void testListMoviesFails() {
        when(service.listMovies())
                .thenAnswer(invocation -> Result.err(new OperationException("Error")));

        String result = movieCommands.listMovies();

        assertThat(result).isEqualTo("An error occured: Error");
    }
}


