package com.example.bookingtrain.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailSeatDTO {
    private int seatId;
    private String seatCode;
    private String coacheCode;
    private String seatTypeName;
    private double price;

    public DetailSeatDTO(int seatId, String seatCode, String coacheCode, String seatTypeName, double price) {
        this.seatId = seatId;
        this.seatCode = seatCode;
        this.coacheCode = coacheCode;
        this.seatTypeName = seatTypeName;
        this.price = price;
    }
}