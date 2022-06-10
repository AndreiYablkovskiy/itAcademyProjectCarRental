package project.EE.service;

import project.EE.model.entity.Order;
import project.EE.model.entity.User;

import java.security.Principal;
import java.util.List;


public interface OrderService {
    void crateNewOrder (Integer carId, String rentalStart, String rentalEnd, Principal principal);
    Order showNewOrder (Principal principal);
    void updateOrderStatus(Integer orderId, Integer statusId);
    void updateOrderStatus(Integer orderId, Integer statusId, String employeeName);
    void updateOrderInfo(Integer orderId, String orderInfo, User employee);
    List<Order> getAllOrders();
    List<Order> getByStatusId(Integer id);
    Order findById(Integer id);
}
