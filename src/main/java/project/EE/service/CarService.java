package project.EE.service;

import project.EE.model.entity.Car;
import project.EE.model.entity.Order;

import java.util.List;

public interface CarService {
    List<Car> findAllWithoutRepairStatus();
    Car findById(Integer id);
    void updateCarStatus(Integer orderId, Integer carStatusId);
}
