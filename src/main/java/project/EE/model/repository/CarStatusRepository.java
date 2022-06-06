package project.EE.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.EE.model.entity.CarStatus;

@Repository
public interface CarStatusRepository extends JpaRepository<CarStatus, Integer> {
}
