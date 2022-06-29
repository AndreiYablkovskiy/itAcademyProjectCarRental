package project.car_rental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.car_rental.model.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByCarStatusIdNot(Integer carStatus);
    List<Car> findByCarStatusIdNotAndMark(Integer carStatus, String mark);
    List<Car> findByCarStatusId(Integer statusId);
    List<Car> findByCarStatusIdAndMark(Integer statusId, String mark);
}
