package project.car_rental.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.car_rental.model.entity.OrderStatus;
import project.car_rental.model.repository.OrderStatusRepository;
import project.car_rental.service.OrderStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus findById(Integer id) {
      return   orderStatusRepository.getById(id);
    }
}
