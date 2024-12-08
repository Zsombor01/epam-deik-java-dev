package com.epam.training.ticketservice.dto;

import com.epam.training.ticketservice.model.User;
import com.epam.training.ticketservice.model.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserDTO {
    private final String username;
    private final UserRole userRole;

    public UserDTO(User dao){
        this.username = dao.getUsername();
        this.userRole = dao.getRole();
    }
}
