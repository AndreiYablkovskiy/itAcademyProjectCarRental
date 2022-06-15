package project.EE.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.EE.model.entity.CarStatus;

import java.util.List;

@Repository
public interface CarStatusRepository extends JpaRepository<CarStatus, Integer> {
    List<CarStatus> findByNameNot(String statusName);
}
