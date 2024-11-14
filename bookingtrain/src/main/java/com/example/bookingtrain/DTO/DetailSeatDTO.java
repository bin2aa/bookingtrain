package com.example.bookingtrain.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailSeatDTO {
    public int seatId;
    public String seatCode;
    public String coachCode;
    public String seatTypeName;
    public int price;

    public DetailSeatDTO(int seatId, String seatCode, String coachCode, String seatTypeName, int price) {
        this.seatId = seatId;
        this.seatCode = seatCode;
        this.coachCode = coachCode;
        this.seatTypeName = seatTypeName;
        this.price = price;
    }

}
