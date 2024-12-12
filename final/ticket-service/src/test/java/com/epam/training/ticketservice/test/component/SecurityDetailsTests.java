package com.epam.training.ticketservice.test.component;

import com.epam.training.ticketservice.component.SecurityDetails;
import com.epam.training.ticketservice.model.User;
import com.epam.training.ticketservice.model.UserRole;
import com.epam.training.ticketservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class SecurityDetailsTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private SecurityDetails securityDetails;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsernameWhenUserExistsAsAdmin() {
        String username = "admin";
        String rawPassword = "admin123";
        User user = new User(username, rawPassword, UserRole.ADMIN);
        when(repository.findByUsername(username)).thenReturn(Optional.of(user));
        when(encoder.encode(rawPassword)).thenReturn("encodedPassword");

        UserDetails userDetails = securityDetails.loadUserByUsername(username);

        assertThat(userDetails.getUsername()).isEqualTo(username);
        assertThat(userDetails.getPassword()).isEqualTo("encodedPassword");
        assertThat(userDetails.getAuthorities()).extracting("authority")
                .containsExactlyInAnyOrder("ROLE_USER", "ROLE_ADMIN");
    }

    @Test
    void testLoadUserByUsernameWhenUserExistsAsRegularUser() {
        String username = "user";
        String rawPassword = "user123";
        User user = new User(username, rawPassword, UserRole.USER);
        when(repository.findByUsername(username)).thenReturn(Optional.of(user));
        when(encoder.encode(rawPassword)).thenReturn("encodedPassword");

        UserDetails userDetails = securityDetails.loadUserByUsername(username);

        assertThat(userDetails.getUsername()).isEqualTo(username);
        assertThat(userDetails.getPassword()).isEqualTo("encodedPassword");
        assertThat(userDetails.getAuthorities()).extracting("authority")
                .containsExactly("ROLE_USER");
    }

    @Test
    void testLoadUserByUsernameWhenUserDoesNotExist() {
        String username = "nonexistent";
        when(repository.findByUsername(username)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> securityDetails.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User does not exist");
    }
}

