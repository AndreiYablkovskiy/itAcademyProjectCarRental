package project.EE.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.EE.model.entity.Car;
import project.EE.model.repository.CarRepository;
import project.EE.service.CarService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public List<Car> findAllWithoutRepairStatus() {
      return   carRepository.findAllWithoutRepairStatus();
    }

    @Override
    public Car findById(Integer id) {
        return carRepository.getById(id);
    }
}
