package project.EE.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import project.EE.model.entity.OrderStatus;
import project.EE.model.repository.OrderStatusRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderStatusServiceImplTest {
    @InjectMocks
    private OrderStatusServiceImpl orderStatusService;
    @Mock
    private OrderStatusRepository orderStatusRepository;

    @Test
    void findAll() {
        List<OrderStatus> orderStatusList = Mockito.mock(List.class);

        Mockito.doReturn(orderStatusList)
                .when(orderStatusRepository)
                .findAll();

        List<OrderStatus> resultList = orderStatusService.findAll();
        Mockito.verify(orderStatusRepository, Mockito.times(1)).findAll();
        assertEquals(orderStatusList, resultList);
    }

    @Test
    void findById() {
        OrderStatus orderStatus = Mockito.mock(OrderStatus.class);

        Mockito.doReturn(orderStatus)
                .when(orderStatusRepository)
                .getById(1);

        OrderStatus result = orderStatusService.findById(1);
        Mockito.verify(orderStatusRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(1));
        assertEquals(orderStatus, result);
    }
}