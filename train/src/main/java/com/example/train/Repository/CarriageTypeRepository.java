package com.example.train.Repository;

import com.example.train.Model.CarriageTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CarriageTypeRepository extends JpaRepository<CarriageTypeModel, Integer>  {
}
