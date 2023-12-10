package com.jtspringproject.JtSpringProject.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jtspringproject.JtSpringProject.dao.cartDao;
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

@ContextConfiguration(classes = {cartService.class})
@ExtendWith(SpringExtension.class)
class cartServiceTest {
    @MockBean
    private cartDao cartDao;

    @Autowired
    private cartService cartService;

    @Test
    void testAddCart() {
        Cart cart = new Cart();
        cart.setCustomer(new User());
        cart.setId(1);
        cart.setProducts(new ArrayList<>());

        User customer = new User();
        customer.setAddress("42 Main St");
        customer.setCart(cart);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setPassword("iloveyou");
        customer.setRole("Role");
        customer.setUsername("janedoe");

        Cart cart2 = new Cart();
        cart2.setCustomer(customer);
        cart2.setId(1);
        cart2.setProducts(new ArrayList<>());

        User customer2 = new User();
        customer2.setAddress("42 Main St");
        customer2.setCart(cart2);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1);
        customer2.setPassword("iloveyou");
        customer2.setRole("Role");
        customer2.setUsername("janedoe");

        Cart cart3 = new Cart();
        cart3.setCustomer(customer2);
        cart3.setId(1);
        cart3.setProducts(new ArrayList<>());
        when(cartDao.addCart(Mockito.<Cart>any())).thenReturn(cart3);

        User customer3 = new User();
        customer3.setAddress("42 Main St");
        customer3.setCart(new Cart());
        customer3.setEmail("jane.doe@example.org");
        customer3.setId(1);
        customer3.setPassword("iloveyou");
        customer3.setRole("Role");
        customer3.setUsername("janedoe");

        Cart cart4 = new Cart();
        cart4.setCustomer(customer3);
        cart4.setId(1);
        cart4.setProducts(new ArrayList<>());

        User customer4 = new User();
        customer4.setAddress("42 Main St");
        customer4.setCart(cart4);
        customer4.setEmail("jane.doe@example.org");
        customer4.setId(1);
        customer4.setPassword("iloveyou");
        customer4.setRole("Role");
        customer4.setUsername("janedoe");

        Cart cart5 = new Cart();
        cart5.setCustomer(customer4);
        cart5.setId(1);
        cart5.setProducts(new ArrayList<>());
        Cart actualAddCartResult = cartService.addCart(cart5);
        verify(cartDao).addCart(Mockito.<Cart>any());
        assertSame(cart3, actualAddCartResult);
    }

    @Test
    void testGetCarts() {
        ArrayList<Cart> cartList = new ArrayList<>();
        when(cartDao.getCarts()).thenReturn(cartList);
        List<Cart> actualCarts = cartService.getCarts();
        verify(cartDao).getCarts();
        assertTrue(actualCarts.isEmpty());
        assertSame(cartList, actualCarts);
    }

    @Test
    void testUpdateCart() {
        doNothing().when(cartDao).updateCart(Mockito.<Cart>any());

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
        cartService.updateCart(cart2);
        verify(cartDao).updateCart(Mockito.<Cart>any());
    }

    @Test
    void testDeleteCart() {
        doNothing().when(cartDao).deleteCart(Mockito.<Cart>any());

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
        cartService.deleteCart(cart2);
        verify(cartDao).deleteCart(Mockito.<Cart>any());
    }

    @Test
    void testGetCartByUserId() {
        Cart cart = new Cart();
        cart.setCustomer(new User());
        cart.setId(1);
        cart.setProducts(new ArrayList<>());

        User customer = new User();
        customer.setAddress("42 Main St");
        customer.setCart(cart);
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setPassword("iloveyou");
        customer.setRole("Role");
        customer.setUsername("janedoe");

        Cart cart2 = new Cart();
        cart2.setCustomer(customer);
        cart2.setId(1);
        cart2.setProducts(new ArrayList<>());

        User customer2 = new User();
        customer2.setAddress("42 Main St");
        customer2.setCart(cart2);
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1);
        customer2.setPassword("iloveyou");
        customer2.setRole("Role");
        customer2.setUsername("janedoe");

        Cart cart3 = new Cart();
        cart3.setCustomer(customer2);
        cart3.setId(1);
        cart3.setProducts(new ArrayList<>());
        when(cartDao.getCartByUserId(anyInt())).thenReturn(cart3);
        Cart actualCartByUserId = cartService.getCartByUserId(1);
        verify(cartDao).getCartByUserId(anyInt());
        assertSame(cart3, actualCartByUserId);
    }
}
