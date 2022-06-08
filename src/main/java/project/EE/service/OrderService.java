package project.EE.service;

import project.EE.model.entity.Order;
import java.security.Principal;
import java.util.List;


public interface OrderService {
    void crateNewOrder (Integer carId, String rentalStart, String rentalEnd, Principal principal);
    Order showNewOrder (Principal principal);
    void updateOrderStatusToPaid(Integer orderId);
    List<Order> getAllOrders();
    void cancelOrder (Integer id);
}
