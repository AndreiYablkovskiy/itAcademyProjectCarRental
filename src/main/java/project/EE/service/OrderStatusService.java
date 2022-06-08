package project.EE.service;

import project.EE.model.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatus> findAll();
    OrderStatus findById(Integer id);
}
