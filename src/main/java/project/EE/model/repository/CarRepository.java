package project.EE.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.EE.model.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByCarStatusIdNot(Integer carStatus);
    List<Car> findByCarStatusId(Integer statusId);
}
