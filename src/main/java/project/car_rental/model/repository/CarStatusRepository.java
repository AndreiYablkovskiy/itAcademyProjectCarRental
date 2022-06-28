package project.car_rental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.car_rental.model.entity.CarStatus;

import java.util.List;

@Repository
public interface CarStatusRepository extends JpaRepository<CarStatus, Integer> {
    List<CarStatus> findByNameNot(String statusName);
}
