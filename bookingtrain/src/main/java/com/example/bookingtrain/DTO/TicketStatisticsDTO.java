package com.example.bookingtrain.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketStatisticsDTO {
    private int year;
    private int month;
    private long ticketCount;
    private long activeTicketCount;

    public TicketStatisticsDTO(int year, int month, long ticketCount, long activeTicketCount) {
        this.year = year;
        this.month = month;
        this.ticketCount = ticketCount;
        this.activeTicketCount = activeTicketCount;
    }
}