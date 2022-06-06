package project.EE.service;

import project.EE.model.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAllWithoutRepairStatus();

    Car findById(Integer id);
}
