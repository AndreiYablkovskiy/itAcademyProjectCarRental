package project.car_rental.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import project.car_rental.model.entity.Car;
import project.car_rental.model.entity.CarStatus;
import project.car_rental.model.repository.CarRepository;
import project.car_rental.service.CarStatusService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;
    private static final Integer REPAIR_STATUS = 3;
    private static final int STATUS_ALL = 0;

    @Mock
    private CarRepository carRepository;
    @Mock
    private CarStatusService carStatusService;
    @Mock
    private List<Car> mockCars;
    @Mock
    private Car mockCar;

    @Test
    void findAllWithoutRepairStatus() {
        Mockito.doReturn(mockCars)
                .when(carRepository)
                .findByCarStatusIdNotOrderByMark(REPAIR_STATUS);

        List<Car> resultList = carService.findAllWithoutRepairStatus();
        Mockito.verify(carRepository, Mockito.times(1)).findByCarStatusIdNotOrderByMark(ArgumentMatchers.eq(REPAIR_STATUS));
        assertEquals(mockCars, resultList);

    }

    @Test
    void findById() {
        Mockito.doReturn(mockCar)
                .when(carRepository)
                .getById(1);

        Car resultCar = carService.findById(1);
        Mockito.verify(carRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(1));
        assertEquals(mockCar, resultCar);
    }

    @Test
    void updateCarStatus() {

        Mockito.doReturn(mockCar)
                .when(carRepository)
                .getById(2);

        CarStatus carStatus = Mockito.mock(CarStatus.class);

        Mockito.doReturn(carStatus)
                .when(carStatusService)
                .findById(3);

        carService.updateCarStatus(2, 3);

        Mockito.verify(carRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(2));
        Mockito.verify(carStatusService, Mockito.times(1)).findById(ArgumentMatchers.eq(3));
        Mockito.verify(mockCar, Mockito.times(1)).setCarStatus(ArgumentMatchers.eq(carStatus));
        Mockito.verify(carRepository, Mockito.times(1)).save(mockCar);
    }

    @Test
    void getByStatusId() {

        Mockito.doReturn(mockCars)
                .when(carRepository)
                .findByCarStatusIdOrderByMark(1);

        List<Car> resultList = carService.getByStatusId(1);
        Mockito.verify(carRepository, Mockito.times(1)).findByCarStatusIdOrderByMark(1);
        assertEquals(mockCars, resultList);
    }

    @Test
    void getCarsByStatusIdIs0() {
        Mockito.doReturn(mockCars)
                .when(carRepository)
                .findByCarStatusIdNotOrderByMark(REPAIR_STATUS);

        List<Car> resultList = carService.getCarsByStatusId(STATUS_ALL);
        Mockito.verify(carRepository, Mockito.times(1)).findByCarStatusIdNotOrderByMark(ArgumentMatchers.eq(REPAIR_STATUS));
        assertEquals(mockCars, resultList);

    }

    @Test
    void getCarsByStatusIdIsNot0() {
        Mockito.doReturn(mockCars)
                .when(carRepository)
                .findByCarStatusIdOrderByMark(2);

        List<Car> resultList = carService.getCarsByStatusId(2);
        Mockito.verify(carRepository, Mockito.times(1)).findByCarStatusIdOrderByMark(ArgumentMatchers.eq(2));
        assertEquals(mockCars, resultList);
    }

    @Test
    void getAllMarksWhereCarStatusNotRepair() {
        carService.getAllMarksWhereCarStatusNotRepair();
        Mockito.verify(carRepository, Mockito.times(1)).findByCarStatusIdNotOrderByMark(ArgumentMatchers.eq(REPAIR_STATUS));

    }
}