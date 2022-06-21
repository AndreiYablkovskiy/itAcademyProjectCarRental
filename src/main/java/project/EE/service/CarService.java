package project.EE.service;

import project.EE.model.entity.Car;
import project.EE.model.entity.Order;

import java.util.List;
import java.util.Set;

public interface CarService {
    List<Car> findAllWithoutRepairStatus();
    Car findById(Integer id);
    void updateCarStatus(Integer carId, Integer carStatusId);
    List<Car> getByStatusId(Integer id);
    List<Car> getCarsByStatusId(Integer statusId);
    Set<String> getAllMarksWhereCarStatusNotRepair();
}
