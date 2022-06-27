package project.EE.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import project.EE.model.entity.Car;
import project.EE.model.entity.Order;
import project.EE.model.entity.OrderStatus;
import project.EE.model.entity.User;
import project.EE.model.repository.OrderRepository;
import project.EE.service.CarService;
import project.EE.service.EmailSenderService;
import project.EE.service.OrderStatusService;
import project.EE.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderServiceImplTest {
    private static final int ORDER_STATUS_ALL = 0;
    public static final int ORDER_STATUS_CREATED = 1;
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderStatusService orderStatusService;
    @Mock
    private UserService userService;
    @Mock
    private CarService carService;
    @Mock
    private EmailSenderService emailSenderService;
    @Mock
    private Principal principal;
    @Mock
    User user;
    @Mock
    Car car;
    @Mock
    OrderStatus orderStatus;
    @Mock
    Order order;
    @Mock
    List<Order> orders;

    @Test
    void createNewOrder() {
        Mockito.doReturn("some@mail.com")
                .when(user)
                .getEmail();

        Mockito.doReturn("John")
                .when(principal)
                .getName();

        Mockito.doReturn(orderStatus)
                .when(orderStatusService)
                .findById(ORDER_STATUS_CREATED);

        Mockito.doReturn(user)
                .when(userService)
                .findByUsername("John");

        Mockito.doReturn(car)
                .when(carService)
                .findById(1);

        boolean isOrderCreated = orderService.createNewOrder(1, "2025-06-26T09:00", "2025-06-27T09:00"
                , principal);

        assertTrue(isOrderCreated);
        assertNotNull(user);
        assertNotNull(car);

        Mockito.verify(orderStatusService, Mockito.times(1)).findById(ArgumentMatchers.eq(ORDER_STATUS_CREATED));
        Mockito.verify(userService, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("John"));
        Mockito.verify(carService, Mockito.times(1)).findById(ArgumentMatchers.eq(1));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any(Order.class));
        Mockito.verify(emailSenderService, Mockito.times(1)).sendEmail(
                ArgumentMatchers.eq(user.getEmail())
                , ArgumentMatchers.contains("Order number ")
                , ArgumentMatchers.contains("Your order has been successfully created")
        );
    }

    @Test
    void createNewOrderWhenRentalStartOrRentalEndIsNotValid() {
        boolean isOrderCreated = orderService.createNewOrder(1, "2022-06-26T09:00"
                , "2022-06-29T09:00", principal);

        assertFalse(isOrderCreated);
    }

    @Test
    void showNewOrder() {
        Mockito.doReturn("John")
                .when(principal)
                .getName();

        Mockito.doReturn(user)
                .when(userService)
                .findByUsername("John");

        Mockito.doReturn(1)
                .when(user)
                .getId();

        Mockito.doReturn(orders)
                .when(orderRepository)
                .findFirst1ByUserId(1, Sort.by("id").descending());

        Mockito.doReturn(order)
                .when(orders)
                .get(0);

        Order result = orderService.showNewOrder(principal);
        Mockito.verify(userService, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("John"));
        Mockito.verify(orderRepository, Mockito.times(1)).findFirst1ByUserId(ArgumentMatchers.eq(1)
                , ArgumentMatchers.eq(Sort.by("id").descending()));
        assertEquals(order, result);

    }

    @Test
    void updateOrderStatus() {
        Mockito.doReturn(order)
                .when(orderRepository)
                .getById(1);

        Mockito.doReturn(orderStatus)
                .when(orderStatusService)
                .findById(2);

        orderService.updateOrderStatus(1, 2);
        Mockito.verify(orderRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(1));
        Mockito.verify(orderStatusService, Mockito.times(1)).findById(2);
        Mockito.verify(order, Mockito.times(1)).setOrderStatus(ArgumentMatchers.eq(orderStatus));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.eq(order));
    }

    @Test
    void testUpdateOrderStatus() {
        Mockito.doReturn("Admin")
                .when(principal)
                .getName();

        Mockito.doReturn(order)
                .when(orderRepository)
                .getById(1);

        Mockito.doReturn(orderStatus)
                .when(orderStatusService)
                .findById(2);

        orderService.updateOrderStatus(1, 2, principal);
        Mockito.verify(orderRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(1));
        Mockito.verify(orderStatusService, Mockito.times(1)).findById(2);
        Mockito.verify(order, Mockito.times(1)).setOrderStatus(ArgumentMatchers.eq(orderStatus));
        Mockito.verify(order, Mockito.times(1)).setEmployeeName(ArgumentMatchers.eq("Admin"));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.eq(order));
    }


    @Test
    void updateOrderInfo() {
        Mockito.doReturn("Admin")
                .when(principal)
                .getName();

        Mockito.doReturn(user)
                .when(userService)
                .findByUsername("Admin");

        Mockito.doReturn("Bill")
                .when(user)
                .getFirstname();

        Mockito.doReturn("Smith")
                .when(user)
                .getSurname();

        Mockito.doReturn(order)
                .when(orderRepository)
                .getById(1);

        Mockito.doReturn("Info")
                .when(order)
                .getOrderInfo();

        orderService.updateOrderInfo(1, "Some info", principal);
        Mockito.verify(userService, Mockito.times(1)).findByUsername(ArgumentMatchers.eq(principal.getName()));
        Mockito.verify(orderRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(1));
        Mockito.verify(order, Mockito.times(1)).setOrderInfo(ArgumentMatchers.eq(order.getOrderInfo() + "\n@" + user.getFirstname()
                + " " + user.getSurname() + ": \nSome info"));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.eq(order));
    }

    @Test
    void getAllOrders() {
        Mockito.doReturn(orders)
                .when(orderRepository)
                .findAll();

        List<Order> result = orderService.getAllOrders();
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
        assertEquals(orders, result);

    }

    @Test
    void getByStatusId() {
        Mockito.doReturn(orders)
                .when(orderRepository)
                .findByOrderStatusId(1, Sort.by("id").descending());

        List<Order> result = orderService.getByStatusId(1);
        Mockito.verify(orderRepository, Mockito.times(1)).findByOrderStatusId(1, Sort.by("id").descending());
        assertEquals(orders, result);
    }

    @Test
    void findById() {
        Optional<Order> order = Optional.of(new Order());

        Mockito.doReturn(order)
                .when(orderRepository)
                .findById(1);

        Optional<Order> result = orderService.findById(1);
        Mockito.verify(orderRepository, Mockito.times(1)).findById(1);
        assertEquals(order, result);
    }

    @Test
    void updateOrderAndCarStatuses() {
        Mockito.doReturn(order)
                .when(orderRepository)
                .getById(3);

        Mockito.doReturn(orderStatus)
                .when(orderStatusService)
                .findById(4);

        orderService.updateOrderAndCarStatuses(1, 2, 3, 4);
        Mockito.verify(orderRepository, Mockito.times(1)).getById(3);
        Mockito.verify(orderStatusService, Mockito.times(1)).findById(4);
        Mockito.verify(order, Mockito.times(1)).setOrderStatus(ArgumentMatchers.eq(orderStatus));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.eq(order));
        Mockito.verify(carService, Mockito.times(1)).updateCarStatus(1, 2);
    }

    @Test
    void testUpdateOrderAndCarStatuses() {
        Mockito.doReturn("Admin")
                .when(principal)
                .getName();

        Mockito.doReturn(order)
                .when(orderRepository)
                .getById(3);

        Mockito.doReturn(orderStatus)
                .when(orderStatusService)
                .findById(4);

        orderService.updateOrderAndCarStatuses(1,2,3,4, principal);
        Mockito.verify(orderRepository, Mockito.times(1)).getById(3);
        Mockito.verify(orderStatusService, Mockito.times(1)).findById(4);
        Mockito.verify(order, Mockito.times(1)).setOrderStatus(ArgumentMatchers.eq(orderStatus));
        Mockito.verify(order, Mockito.times(1)).setEmployeeName(ArgumentMatchers.eq("Admin"));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.eq(order));
        Mockito.verify(carService, Mockito.times(1)).updateCarStatus(1,2);
    }

    @Test
    void testUpdateOrderAndCarStatusesWhenCarStatusIdAndCarIdIsNull(){
        Mockito.doReturn("Admin")
                .when(principal)
                .getName();

        Mockito.doReturn(order)
                .when(orderRepository)
                .getById(3);

        Mockito.doReturn(orderStatus)
                .when(orderStatusService)
                .findById(4);

        orderService.updateOrderAndCarStatuses(null,null,3,4, principal);
        Mockito.verify(orderRepository, Mockito.times(1)).getById(3);
        Mockito.verify(orderStatusService, Mockito.times(1)).findById(4);
        Mockito.verify(order, Mockito.times(1)).setOrderStatus(ArgumentMatchers.eq(orderStatus));
        Mockito.verify(order, Mockito.times(1)).setEmployeeName(ArgumentMatchers.eq("Admin"));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.eq(order));
        Mockito.verify(carService, Mockito.times(0)).updateCarStatus(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt());

    }

    @Test
    void getOrdersByStatusWhenOrderStatusIs_ORDER_STATUS_ALL() {
        Mockito.doReturn(orders)
                .when(orderRepository)
                .findAll();

        List<Order> result = orderService.getOrdersByStatus(ORDER_STATUS_ALL);
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
        Mockito.verify(orderRepository, Mockito.times(0)).findByOrderStatusId(ArgumentMatchers.anyInt(), ArgumentMatchers.any(Sort.class));
        assertEquals(orders, result);

    }
    @Test
    void getOrdersByStatus(){
        Mockito.doReturn(orders)
                .when(orderRepository)
                .findByOrderStatusId(1, Sort.by("id").descending());

        List<Order> result = orderService.getOrdersByStatus(1);
        Mockito.verify(orderRepository, Mockito.times(1)).findByOrderStatusId(1, Sort.by("id").descending());
        Mockito.verify(orderRepository, Mockito.times(0)).findAll();
        assertEquals(orders, result);

    }
}