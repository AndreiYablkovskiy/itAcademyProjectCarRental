package project.EE.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.EE.model.entity.Car;
import project.EE.model.entity.CarStatus;
import project.EE.model.repository.CarRepository;
import project.EE.service.CarService;
import project.EE.service.CarStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarStatusService carStatusService;

    @Override
    public List<Car> findAllWithoutRepairStatus() {
      return   carRepository.findAllWithoutRepairStatus();
    }

    @Override
    public Car findById(Integer id) {
        return carRepository.getById(id);
    }

    @Override
    public void updateCarStatus(Integer carId, Integer carStatusId) {
        Car car = carRepository.getById(carId);
        CarStatus carStatus = carStatusService.findById(carStatusId);
        car.setCarStatus(carStatus);
        carRepository.save(car);
    }
}
