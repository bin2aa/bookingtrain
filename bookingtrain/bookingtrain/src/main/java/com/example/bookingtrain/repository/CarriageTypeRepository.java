package com.example.bookingtrain.repository;

import com.example.bookingtrain.model.CarriageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarriageTypeRepository extends JpaRepository<CarriageType, Long> {
    CarriageType findByCarriageTypeName(String CarriageTypeName);

}
