package project.EE.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.EE.model.entity.OrderStatus;
import project.EE.model.repository.OrderStatusRepository;
import project.EE.service.OrderStatusService;

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
