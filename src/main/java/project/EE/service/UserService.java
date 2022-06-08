package project.EE.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import project.EE.model.entity.Order;
import project.EE.model.entity.User;

import java.util.List;


public interface UserService extends UserDetailsService{
     boolean saveUser(User user);
     User findByUsername(String username);
     List<Order> findOrdersByUserId (Integer userId);
     Order findUsersOrder(Integer id);
     User findById(Integer id);
     boolean updateUser(User user);
}
