package project.EE.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.EE.model.entity.Car;
import project.EE.model.entity.CarStatus;
import project.EE.model.repository.CarRepository;
import project.EE.service.CarService;
import project.EE.service.CarStatusService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    public static final Integer REPAIR_STATUS = 3;
    public static final Integer STATUS_ALL = 0;
    private final CarRepository carRepository;
    private final CarStatusService carStatusService;

    @Override
    public List<Car> findAllWithoutRepairStatus() {
      return   carRepository.findByCarStatusIdNot(REPAIR_STATUS);
    }

    @Override
    public Car findById(Integer id) {
        return carRepository.getById(id);
    }

    @Override
    @Transactional
    public void updateCarStatus(Integer carId, Integer carStatusId) {
        Car car = carRepository.getById(carId);
        CarStatus carStatus = carStatusService.findById(carStatusId);
        car.setCarStatus(carStatus);
        carRepository.save(car);
    }

    @Override
    public List<Car> getByStatusId(Integer id) {
        return carRepository.findByCarStatusId(id);
    }

    @Override
    public List<Car> getCarsByStatusId(Integer statusId) {
        List<Car> cars;
        if (statusId.equals(STATUS_ALL)) {
            cars = findAllWithoutRepairStatus();
        } else {
            cars = getByStatusId(statusId);
        }
        return cars;
    }

    @Override
    public Set<String> getAllMarksWhereCarStatusNotRepair() {
        Set<String> carMarks = new HashSet<>();
        List<Car> cars = carRepository.findByCarStatusIdNot(REPAIR_STATUS);
        for (Car car : cars) {
            carMarks.add(car.getMark());
        }
        return carMarks;
    }

}
