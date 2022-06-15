package project.EE.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.EE.model.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query(value = "SELECT * FROM car_rental.car where car_status_id not in ('3')", nativeQuery = true)
    List<Car> findAllWithoutRepairStatus();
    List<Car> findByCarStatusId(Integer statusId);
}
