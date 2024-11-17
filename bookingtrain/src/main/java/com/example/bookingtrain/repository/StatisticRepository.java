package com.example.bookingtrain.repository;

import com.example.bookingtrain.DTO.StationArrivalStatistic;
import com.example.bookingtrain.DTO.TrainRunStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.bookingtrain.model.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Schedule, Integer> {

    // Modified to include the month filter
    @Query(value = """
    SELECT new com.example.bookingtrain.DTO.TrainRunStatistic(
        MONTH(s.startDeparture),
        YEAR(s.startDeparture),
        s.train.trainName,
        COUNT(*)
    )
    FROM Schedule s
    WHERE s.statusSchedule = 1 
    AND MONTH(s.startDeparture) = :month
    GROUP BY YEAR(s.startDeparture), MONTH(s.startDeparture), s.train.trainId 
    ORDER BY YEAR(s.startDeparture), MONTH(s.startDeparture), COUNT(*) DESC
    """)
    List<TrainRunStatistic> countTrainRunsByMonth(int month);

    // Modified to include the month filter
    @Query(value = """
        SELECT new com.example.bookingtrain.DTO.StationArrivalStatistic(
            s.stationArrival.stationName,
            COUNT(*) 
        ) 
        FROM Schedule s 
        WHERE MONTH(s.startDeparture) = :month
        GROUP BY s.stationArrival.stationId 
        ORDER BY COUNT(*) DESC
    """)
    List<StationArrivalStatistic> countArrivalByStation(int month);
}
