package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.DetailSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailScheduleRepository extends JpaRepository<DetailSchedule, Long> {
    // DetailShedule findByDetailShedulename(String DetailShedule);

}
