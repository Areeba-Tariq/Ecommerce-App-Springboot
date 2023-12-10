package com.jtspringproject.JtSpringProject.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.User;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {userService.class})
@ExtendWith(SpringExtension.class)
class userServiceTest {
    @MockBean
    private userDao userDao;

    @Autowired
    private userService userService;

    @Test
    void testGetUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(userDao.getAllUser()).thenReturn(userList);
        List<User> actualUsers = userService.getUsers();
        verify(userDao).getAllUser();
        assertTrue(actualUsers.isEmpty());
        assertSame(userList, actualUsers);
    }

    @Test
    void testGetUserById() {
        User customer = new User();
        customer.setAddress("42 Main St");
        customer.setCart(new Cart());
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setPassword("iloveyou");
        customer.setRole("Role");
        customer.setUsername("janedoe");

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setId(1);
        cart.setProducts(new ArrayList<>());

        User customer2 = new User();
        customer2.setAddress("42 Main St");
        customer2.setCart(cart);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1);
        customer2.setPassword("iloveyou");
        customer2.setRole("Role");
        customer2.setUsername("janedoe");

        Cart cart2 = new Cart();
        cart2.setCustomer(customer2);
        cart2.setId(1);
        cart2.setProducts(new ArrayList<>());

        User user = new User();
        user.setAddress("42 Main St");
        user.setCart(cart2);
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userDao.getUserById(anyInt())).thenReturn(user);
        User actualUserById = userService.getUserById(1);
        verify(userDao).getUserById(anyInt());
        assertSame(user, actualUserById);
    }

    @Test
    void testAddUser() {
        User customer = new User();
        customer.setAddress("42 Main St");
        customer.setCart(new Cart());
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setPassword("iloveyou");
        customer.setRole("Role");
        customer.setUsername("janedoe");

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setId(1);
        cart.setProducts(new ArrayList<>());

        User customer2 = new User();
        customer2.setAddress("42 Main St");
        customer2.setCart(cart);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1);
        customer2.setPassword("iloveyou");
        customer2.setRole("Role");
        customer2.setUsername("janedoe");

        Cart cart2 = new Cart();
        cart2.setCustomer(customer2);
        cart2.setId(1);
        cart2.setProducts(new ArrayList<>());

        User user = new User();
        user.setAddress("42 Main St");
        user.setCart(cart2);
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userDao.saveUser(Mockito.<User>any())).thenReturn(user);

        Cart cart3 = new Cart();
        cart3.setCustomer(new User());
        cart3.setId(1);
        cart3.setProducts(new ArrayList<>());

        User customer3 = new User();
        customer3.setAddress("42 Main St");
        customer3.setCart(cart3);
        customer3.setEmail("jane.doe@example.org");
        customer3.setId(1);
        customer3.setPassword("iloveyou");
        customer3.setRole("Role");
        customer3.setUsername("janedoe");

        Cart cart4 = new Cart();
        cart4.setCustomer(customer3);
        cart4.setId(1);
        cart4.setProducts(new ArrayList<>());

        User user2 = new User();
        user2.setAddress("42 Main St");
        user2.setCart(cart4);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUsername("janedoe");
        User actualAddUserResult = userService.addUser(user2);
        verify(userDao).saveUser(Mockito.<User>any());
        assertSame(user, actualAddUserResult);
    }

    @Test
    void testCheckLogin() {
        User customer = new User();
        customer.setAddress("42 Main St");
        customer.setCart(new Cart());
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setPassword("iloveyou");
        customer.setRole("Role");
        customer.setUsername("janedoe");

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setId(1);
        cart.setProducts(new ArrayList<>());

        User customer2 = new User();
        customer2.setAddress("42 Main St");
        customer2.setCart(cart);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1);
        customer2.setPassword("iloveyou");
        customer2.setRole("Role");
        customer2.setUsername("janedoe");

        Cart cart2 = new Cart();
        cart2.setCustomer(customer2);
        cart2.setId(1);
        cart2.setProducts(new ArrayList<>());

        User user = new User();
        user.setAddress("42 Main St");
        user.setCart(cart2);
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userDao.getUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        User actualCheckLoginResult = userService.checkLogin("janedoe", "iloveyou");
        verify(userDao).getUser(Mockito.<String>any(), Mockito.<String>any());
        assertSame(user, actualCheckLoginResult);
    }

    @Test
    void testDeleteUser() {
        when(userDao.deletUser(anyInt())).thenReturn(true);
        boolean actualDeleteUserResult = userService.deleteUser(1);
        verify(userDao).deletUser(anyInt());
        assertTrue(actualDeleteUserResult);
    }

    @Test
    void testDeleteUser2() {
        when(userDao.deletUser(anyInt())).thenReturn(false);
        boolean actualDeleteUserResult = userService.deleteUser(1);
        verify(userDao).deletUser(anyInt());
        assertFalse(actualDeleteUserResult);
    }
}
