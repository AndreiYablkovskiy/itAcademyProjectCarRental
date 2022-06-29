package project.car_rental.service;

import project.car_rental.model.entity.CarStatus;
import java.util.List;

public interface CarStatusService {
    CarStatus findById(Integer id);
    List<CarStatus> findAllWithoutRepairStatus();
}
