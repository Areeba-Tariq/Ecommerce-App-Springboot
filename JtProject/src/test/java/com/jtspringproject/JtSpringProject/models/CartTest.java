package com.jtspringproject.JtSpringProject.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CartTest {
    @Test
    void testConstructor() {
        Cart actualCart = new Cart();
        Cart cart = new Cart();
        cart.setCustomer(new User());
        cart.setId(1);
        ArrayList<Product> products = new ArrayList<>();
        cart.setProducts(products);
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
        actualCart.setCustomer(customer2);
        actualCart.setId(1);
        ArrayList<Product> products2 = new ArrayList<>();
        actualCart.setProducts(products2);
        User actualCustomer = actualCart.getCustomer();
        int actualId = actualCart.getId();
        List<Product> actualProducts = actualCart.getProducts();
        assertEquals(1, actualId);
        assertEquals(products, actualProducts);
        assertSame(customer2, actualCustomer);
        assertSame(products2, actualProducts);
    }

    @Test
    void testAddProduct() {
        Cart cart = new Cart();

        Category category = new Category();
        category.setId(1);
        category.setName("Name");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setId(1);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(1);
        product.setQuantity(1);
        product.setWeight(3);
        cart.addProduct(product);
        assertEquals(1, cart.getProducts().size());
    }

    @Test
    void testRemoveProduct() {
        Cart cart = new Cart();

        Category category = new Category();
        category.setId(1);
        category.setName("Name");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setId(1);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(1);
        product.setQuantity(1);
        product.setWeight(3);
        cart.removeProduct(product);
        assertTrue(cart.getProducts().isEmpty());
    }
}
