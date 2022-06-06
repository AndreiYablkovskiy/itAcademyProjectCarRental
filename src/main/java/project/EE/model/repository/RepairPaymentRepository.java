package project.EE.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.EE.model.entity.RepairPayment;

@Repository
public interface RepairPaymentRepository extends JpaRepository<RepairPayment, Integer> {
}
