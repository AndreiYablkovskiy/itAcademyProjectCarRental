package project.car_rental.service;

import project.car_rental.model.entity.Car;
import java.util.List;
import java.util.Set;

public interface CarService {
    List<Car> findAllWithoutRepairStatus();
    List<Car> findAllByMarkWithoutRepairStatus(String mark);
    Car findById(Integer id);
    void updateCarStatus(Integer carId, Integer carStatusId);
    List<Car> getByStatusId(Integer id);
    List<Car> getCarsByStatusId(Integer statusId);
    List<Car> getCarsByStatusIdAndMark(Integer statusId, String mark);
    Set<String> getAllMarksWhereCarStatusNotRepair();
}
