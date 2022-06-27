package project.EE.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.EE.model.entity.Order;
import project.EE.model.entity.Role;
import project.EE.model.entity.User;
import project.EE.model.repository.OrderRepository;
import project.EE.model.repository.UserRepository;
import project.EE.model.security.Encoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    public static final String ROLE_USER = "ROLE_USER";
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private Encoder encoder;
    @Mock
    User user;

    @Test
    void saveUserWhenUserFromDBNotNull() {
        User user = new User();
        user.setUsername("Bill");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername("Bill");

        boolean isUserSaved = userService.saveUserOrUpdate(user);
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername("Bill");
        assertFalse(isUserSaved);
    }

    @Test
    void saveUser() {
        BCryptPasswordEncoder bcrypt = Mockito.mock(BCryptPasswordEncoder.class);

        User user = new User();
        user.setUsername("Bill");
        user.setPassword("12345678");

        Mockito.doReturn(bcrypt)
                .when(encoder)
                .bCryptPasswordEncoder();

        Mockito.doReturn("87654321")
                .when(bcrypt)
                .encode(user.getPassword());


        boolean isUserSaved = userService.saveUserOrUpdate(user);
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername("Bill");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        assertNotNull(user.getRoles());
        assertTrue(isUserSaved);
    }

    @Test
    void findByUsername() {
        Mockito.doReturn(user)
                .when(userRepository)
                .findByUsername("Bill");

        User result = userService.findByUsername("Bill");
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("Bill"));
        assertEquals(user, result);

    }

    @Test
    void findOrdersByUserId() {
        List<Order> orders = Mockito.mock(List.class);

        Mockito.doReturn(orders)
                .when(orderRepository)
                .findByUserId(1, Sort.by("id").descending());

        List<Order> result = userService.findOrdersByUserId(1);
        Mockito.verify(orderRepository, Mockito.times(1)).findByUserId(ArgumentMatchers.eq(1)
                , ArgumentMatchers.eq(Sort.by("id").descending()));
        assertEquals(orders, result);
    }

    @Test
    void findUserOrder() {
        Order order = Mockito.mock(Order.class);

        Mockito.doReturn(order)
                .when(orderRepository)
                .getById(1);

        Order result = userService.findUserOrder(1);
        Mockito.verify(orderRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(1));
        assertEquals(order, result);
    }

    @Test
    void findById() {
        Mockito.doReturn(user)
                .when(userRepository)
                .getById(2);

        User result = userService.findById(2);
        Mockito.verify(userRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(2));
        assertEquals(user, result);
    }

    @Test
    void loadUserByUsernameWhereUserIsNull() {
        Mockito.doReturn(null)
                .when(userRepository)
                .findByUsername("SuperBilly");

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("SuperBilly"));
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("SuperBilly"));
    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setUsername("Bill");
        user.setPassword("12345678");
        user.setRoles(Collections.singletonList(new Role(1, ROLE_USER)));

        org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getGrantedAuthorityFromRoles(user.getRoles()));

        Mockito.doReturn(user)
                .when(userRepository)
                .findByUsername("Bill");

        UserDetails result = userService.loadUserByUsername("Bill");
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("Bill"));
        assertEquals(springUser, result);
    }

         Collection<? extends GrantedAuthority> getGrantedAuthorityFromRoles(Collection<Role> roles) {
             return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
         }

    @Test
    void getGrantedAuthorityFromRoles() {
        User user = new User();
        user.setRoles(Collections.singletonList(new Role(1, ROLE_USER)));
        List<SimpleGrantedAuthority> sgaList = new ArrayList<>();
        sgaList.add(new SimpleGrantedAuthority(ROLE_USER));

        Collection<? extends GrantedAuthority> result = userService.getGrantedAuthorityFromRoles(user.getRoles());
        assertEquals(sgaList, result);
    }
}