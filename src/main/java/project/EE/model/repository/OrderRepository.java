package project.EE.model.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.EE.model.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Integer> {
    List<Order> findFirst1ByUserId (Integer id, Sort sort);
    List<Order> findByUserId (Integer userId, Sort sort);

}
