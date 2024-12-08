package com.epam.training.ticketservice.dto;

import com.epam.training.ticketservice.model.Screening;

public class ScreeningDto {
    private final MovieDto movie;
    private final String roomName;
    private final String startTime;

    public ScreeningDto(Screening dao) {
        this.movie = new MovieDto(dao.getMovie());
        this.roomName = dao.getRoom().getName();
        this.startTime = dao.getStartTime().format(Screening.TIME_FORMAT);
    }

    public String toString() {
        return movie.getTitle() + " (" + movie.getCategory() + ", " + movie.getLength()
            + " minutes), screened in room" + roomName + ", at " + startTime;
    }
}
