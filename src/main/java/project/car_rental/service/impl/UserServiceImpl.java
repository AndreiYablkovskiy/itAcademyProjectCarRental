package project.car_rental.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.car_rental.model.entity.Order;
import project.car_rental.model.repository.OrderRepository;
import project.car_rental.model.security.Encoder;
import project.car_rental.model.entity.Role;
import project.car_rental.model.entity.User;
import project.car_rental.model.repository.UserRepository;
import project.car_rental.service.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final Encoder encoder;

    @Override
    @Transactional
    public boolean saveUserOrUpdate(User user) {
        User userFromDB = userRepository.findByUsername((user.getUsername()));
        if (userFromDB != null) {
           return false;
        }
        user.setRoles(Collections.singletonList(new Role(1, ROLE_USER)));
        user.setPassword(encoder.bCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Order> findOrdersByUserId(Integer userId) {
       return orderRepository.findByUserId(userId, Sort.by("id").descending());
    }

    @Override
    public Order findUserOrder(Integer id) {
      return   orderRepository.getById(id);
    }

    @Override
    public User findById(Integer id) {
      return   userRepository.getById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getGrantedAuthorityFromRoles(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorityFromRoles(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}

