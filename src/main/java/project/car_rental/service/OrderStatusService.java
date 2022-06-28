package project.car_rental.service;

import project.car_rental.model.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatus> findAll();
    OrderStatus findById(Integer id);
}
