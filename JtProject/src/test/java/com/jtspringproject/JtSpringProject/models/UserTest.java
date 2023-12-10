package com.jtspringproject.JtSpringProject.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testSetAddress() {
        User user = new User();
        user.setAddress("42 Main St");
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
        user.setCart(cart2);
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUsername("janedoe");
        String actualAddress = user.getAddress();
        String actualEmail = user.getEmail();
        int actualId = user.getId();
        String actualPassword = user.getPassword();
        String actualRole = user.getRole();
        assertEquals("42 Main St", actualAddress);
        assertEquals("Role", actualRole);
        assertEquals("iloveyou", actualPassword);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals("janedoe", user.getUsername());
        assertEquals(1, actualId);
    }
}
