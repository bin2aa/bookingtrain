package com.example.bookingtrain.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDTO {
    private String trainCode;
    private String trainName;
    private String dateStart;
    private String dateEnd;
    private String startTime;
    private String endTime;
    private String departureStation;
    private String arrivalStation;
}