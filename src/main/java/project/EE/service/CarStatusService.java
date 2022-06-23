package project.EE.service;

import project.EE.model.entity.Car;
import project.EE.model.entity.CarStatus;

import java.util.List;

public interface CarStatusService {
    CarStatus findById(Integer id);
    List<CarStatus> findAllWithoutRepairStatus();
}
