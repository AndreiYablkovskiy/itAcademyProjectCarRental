package project.car_rental.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import project.car_rental.model.entity.CarStatus;
import project.car_rental.model.repository.CarStatusRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarStatusServiceImplTest {
    @InjectMocks
    private CarStatusServiceImpl carStatusService;
    private static final String REPAIR_STATUS = "repair";
    @Mock
    private  CarStatusRepository carStatusRepository;

    @Test
    void findById() {
        CarStatus MockCarStatus = Mockito.mock(CarStatus.class);

        Mockito.doReturn(MockCarStatus)
                .when(carStatusRepository)
                .getById(2);

        CarStatus result = carStatusService.findById(2);
        Mockito.verify(carStatusRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(2));
        assertEquals(MockCarStatus, result);


    }

    @Test
    void findAllWithoutRepairStatus() {
        List<CarStatus> mockList = Mockito.mock(List.class);

        Mockito.doReturn(mockList)
                .when(carStatusRepository)
                .findByNameNot(REPAIR_STATUS);

        List<CarStatus> resultList = carStatusService.findAllWithoutRepairStatus();
        Mockito.verify(carStatusRepository, Mockito.times(1)).findByNameNot(ArgumentMatchers.eq(REPAIR_STATUS));
        assertEquals(mockList, resultList);

    }
}