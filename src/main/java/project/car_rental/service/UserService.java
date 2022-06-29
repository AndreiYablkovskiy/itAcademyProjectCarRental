package project.car_rental.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import project.car_rental.model.entity.Order;
import project.car_rental.model.entity.User;
import java.util.List;


public interface UserService extends UserDetailsService{
     boolean saveUserOrUpdate(User user);
     User findByUsername(String username);
     List<Order> findOrdersByUserId (Integer userId);
     Order findUserOrder(Integer id);
     User findById(Integer id);
}
