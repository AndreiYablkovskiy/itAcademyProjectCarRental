package project.car_rental.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.car_rental.model.entity.Car;
import project.car_rental.model.entity.CarStatus;
import project.car_rental.model.repository.CarRepository;
import project.car_rental.service.CarService;
import project.car_rental.service.CarStatusService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    public static final Integer REPAIR_STATUS = 3;
    public static final Integer STATUS_ALL = 0;
    public static final String CAR_MARK_ALL = "all";
    private final CarRepository carRepository;
    private final CarStatusService carStatusService;

    @Override
    public List<Car> findAllWithoutRepairStatus() {
      return   carRepository.findByCarStatusIdNotOrderByMark(REPAIR_STATUS);
    }

    @Override
    public List<Car> findAllByMarkWithoutRepairStatus(String mark) {
        return   carRepository.findByCarStatusIdNotAndMarkOrderByMark(REPAIR_STATUS, mark);
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
        return carRepository.findByCarStatusIdOrderByMark(id);
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
    public List<Car> getCarsByStatusIdAndMark(Integer statusId, String mark) {
        List<Car> cars;
        if (statusId.equals(STATUS_ALL) && mark.equals(CAR_MARK_ALL)) {
            cars = findAllWithoutRepairStatus();
        } else if (statusId.equals(STATUS_ALL)) {
            cars = findAllByMarkWithoutRepairStatus(mark);
        } else if (mark.equals(CAR_MARK_ALL)) {
            cars = carRepository.findByCarStatusIdOrderByMark(statusId);
        } else {
            cars = carRepository.findByCarStatusIdAndMark(statusId, mark);
        }
        return cars;
    }

    @Override
    public Set<String> getAllMarksWhereCarStatusNotRepair() {
        Set<String> carMarks = new HashSet<>();
        List<Car> cars = carRepository.findByCarStatusIdNotOrderByMark(REPAIR_STATUS);
        for (Car car : cars) {
            carMarks.add(car.getMark());
        }
        return carMarks;
    }
}
