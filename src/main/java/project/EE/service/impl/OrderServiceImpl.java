package project.EE.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.EE.model.entity.Car;
import project.EE.model.entity.Order;
import project.EE.model.entity.OrderStatus;
import project.EE.model.entity.User;
import project.EE.model.repository.OrderRepository;
import project.EE.service.CarService;
import project.EE.service.OrderService;
import project.EE.service.UserService;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CarService carService;


    @Override
    public void crateNewOrder(Integer carId, String rentalStart, String rentalEnd, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Car car = carService.findById(carId);
        Order order = new Order();
        order.setUser(user);
        order.setCar(car);
        order.setOrderStatus(new OrderStatus(1, "created"));
        order.setOrderDate(LocalDateTime.now().withNano(0));
        order.setRentalStart(LocalDateTime.parse(rentalStart));
        order.setRentalEnd(LocalDateTime.parse(rentalEnd));
        Duration duration = Duration.between(order.getRentalStart(), order.getRentalEnd());
        long rentalDuration = duration.toHours();
        order.setPaymentValue(order.getCar().getCostForOneHour() * rentalDuration);
        orderRepository.save(order);
    }

    @Override
    public Order showNewOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Order> orders = orderRepository.findFirst1ByUserId(user.getId(), Sort.by("id").descending());
        return orders.get(0);
    }

    @Override
    public void updateOrderStatusToPaid(Integer orderId) {
        Order order = orderRepository.getById(orderId);
        order.setOrderStatus(new OrderStatus(7,"paid"));
        orderRepository.save(order);
    }
}
