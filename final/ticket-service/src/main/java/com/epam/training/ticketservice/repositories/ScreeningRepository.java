package com.epam.training.ticketservice.repositories;

import com.epam.training.ticketservice.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    boolean existsByMovieTitleAndRoomNameAndStartTime(String title, String room, LocalDateTime start);
}
