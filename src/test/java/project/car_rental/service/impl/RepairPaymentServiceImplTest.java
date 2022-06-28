package project.car_rental.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import project.car_rental.model.entity.Order;
import project.car_rental.model.entity.RepairPayment;
import project.car_rental.model.repository.RepairPaymentRepository;
import project.car_rental.service.CarService;
import project.car_rental.service.OrderService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepairPaymentServiceImplTest {
    @InjectMocks
    private RepairPaymentServiceImpl repairPaymentService;
    @Mock
    private RepairPaymentRepository repairPaymentRepository;
    @Mock
    private OrderService orderService;
    @Mock
    private CarService carService;
    @Mock
    RepairPayment repairPayment;

    @Test
    void save() {
        repairPaymentService.save(repairPayment);

        Mockito.verify(repairPaymentRepository, Mockito.times(1)).save(ArgumentMatchers.eq(repairPayment));
    }

    @Test
    void findById() {
        Mockito.doReturn(repairPayment)
                .when(repairPaymentRepository)
                .getById(1);

        RepairPayment result = repairPaymentService.findById(1);

        Mockito.verify(repairPaymentRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(1));
        assertEquals(repairPayment, result);
    }

    @Test
    void createRepairPaymentForOrder() {
        Optional<Order> order = Optional.of(new Order());

        Mockito.doReturn(order)
                .when(orderService)
                .findById(1);

        RepairPayment result = repairPaymentService.createRepairPaymentForOrder(1);

        Mockito.verify(orderService, Mockito.times(1)).findById(ArgumentMatchers.eq(1));
        assertEquals(order.get(), result.getOrder());

    }

    @Test
    void saveRepairPaymentAndUpdateCarStatus() {
        repairPaymentService.saveRepairPaymentAndUpdateCarStatus(1, 2, repairPayment);

        Mockito.verify(carService, Mockito.times(1)).updateCarStatus(ArgumentMatchers.eq(1)
                ,ArgumentMatchers.eq(2));
        Mockito.verify(repairPaymentRepository, Mockito.times(1)).save(ArgumentMatchers.eq(repairPayment));

    }
}