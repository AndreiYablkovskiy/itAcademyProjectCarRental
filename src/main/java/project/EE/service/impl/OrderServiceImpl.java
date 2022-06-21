package project.EE.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.EE.model.entity.Car;
import project.EE.model.entity.Order;
import project.EE.model.entity.OrderStatus;
import project.EE.model.entity.User;
import project.EE.model.repository.OrderRepository;
import project.EE.service.*;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    public static final int ORDER_STATUS_ALL = 0;
    private final OrderRepository orderRepository;
    private final OrderStatusService orderStatusService;
    private final UserService userService;
    private final CarService carService;
    private final EmailSenderService emailSenderService;


    @Override
    @Transactional
    public boolean crateNewOrder(Integer carId, String rentalStart, String rentalEnd, Principal principal) {
        LocalDateTime start = LocalDateTime.parse(rentalStart);
        LocalDateTime end = LocalDateTime.parse(rentalEnd);
        if(start.isAfter(end) || start.isBefore(LocalDateTime.now())){
            return false;
        }
            OrderStatus orderStatus = orderStatusService.findById(1);
            User user = userService.findByUsername(principal.getName());
            Car car = carService.findById(carId);
            Order order = new Order();
            order.setUser(user);
            order.setCar(car);
            order.setOrderStatus(orderStatus);
            order.setOrderDate(LocalDateTime.now().withNano(0));
            order.setRentalStart(start);
            order.setRentalEnd(end);
            order.setOrderInfo(" ");
            Duration duration = Duration.between(order.getRentalStart(), order.getRentalEnd());
            long rentalDuration = duration.toHours();
            order.setPaymentValue(order.getCar().getCostForOneHour() * rentalDuration);
            orderRepository.save(order);
            emailSenderService.sendEmail(user.getEmail(),"Order number " +order.getId().toString()
            ,"Your order has been successfully created.");
            return true;
    }

    @Override
    public Order showNewOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Order> orders = orderRepository.findFirst1ByUserId(user.getId(), Sort.by("id").descending());
        return orders.get(0);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Integer orderId, Integer statusId) {
        Order order = orderRepository.getById(orderId);
        OrderStatus orderStatus = orderStatusService.findById(statusId);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Integer orderId, Integer statusId, Principal principal) {
        String employeeName = principal.getName();
        Order order = orderRepository.getById(orderId);
        OrderStatus orderStatus = orderStatusService.findById(statusId);
        order.setOrderStatus(orderStatus);
        order.setEmployeeName(employeeName);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrderInfo(Integer orderId, String orderInfo, Principal principal) {
        User employee = userService.findByUsername(principal.getName());
        Order order = orderRepository.getById(orderId);
        order.setOrderInfo(order.getOrderInfo()+ "\n@"+employee.getFirstname()+ " " +employee.getSurname()+ ": \n"+orderInfo);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
      return   orderRepository.findAll();
    }

    @Override
    public List<Order> getByStatusId(Integer id) {
     return    orderRepository.findByOrderStatusId(id, Sort.by("id").descending());
    }

    @Override
    public Optional<Order> findById(Integer id) {
      return   orderRepository.findById(id);
    }

    @Override
    public void updateOrderAndCarStatuses(Integer carId, Integer carStatusId, Integer orderId, Integer statusId) {
        updateOrderStatus(orderId, statusId);
        carService.updateCarStatus(carId,carStatusId);
    }

    @Override
    public void updateOrderAndCarStatuses(Integer carId, Integer carStatusId, Integer orderId, Integer statusId, Principal principal) {
        updateOrderStatus(orderId, statusId, principal);
        if (carStatusId != null && carId != null) {
            carService.updateCarStatus(carId, carStatusId);
        }
    }

    @Override
    public List<Order> getOrdersByStatus(Integer statusId) {
        List<Order> orders;
        if (statusId.equals(ORDER_STATUS_ALL)) {
            orders = getAllOrders();
        } else {
            orders = getByStatusId(statusId);
        }
        return orders;
    }
}
