package com.jtspringproject.JtSpringProject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.cartService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.utils.SessionData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private cartService cartService;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @MockBean
    private productService productService;

    @MockBean
    private SessionData sessionData;

    @Autowired
    private UserController userController;

    @MockBean
    private userService userService;

    @Test
    void testRegisterUser() {

        assertEquals("register", (new UserController()).registerUser());
    }

    @Test
    void testBuy() {
        assertEquals("buy", (new UserController()).buy());
    }
    @Test
    void testAddToCart() throws Exception {
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
        when(productService.getProduct(anyInt())).thenReturn(product);
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.post("/products/addtocart")
                .param("productId", "https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("userId", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
    @Test
    void testAdminHome() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/adminhome");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("adminHome"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("adminHome"));
    }
    @Test
    void testAdminHome2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/adminhome");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("adminHome"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("adminHome"));
    }
    @Test
    void testDeleteCartProduct() throws Exception {
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
        when(sessionData.getCurrentUser()).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/delete");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("cartproduct"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("cartproduct"));
    }
    @Test
    void testGetCart() throws Exception {
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
        when(sessionData.getCurrentUser()).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("cartproduct"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("cartproduct"));
    }
    @Test
    void testGetLogin() throws Exception {
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
        when(sessionData.getCurrentUser()).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logout");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("userLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userLogin"));
    }
    @Test
    void testGetUserProfile() throws Exception {
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
        when(sessionData.getCurrentUser()).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user-profile");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("updateProfile"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("updateProfile"));
    }
    @Test
    void testGetproduct() throws Exception {
        when(productService.getProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/products");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("msg"))
                .andExpect(MockMvcResultMatchers.view().name("uproduct"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("uproduct"));
    }
    @Test
    void testGetproduct2() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("?");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setId(1);
        product.setImage("?");
        product.setName("?");
        product.setPrice(1);
        product.setQuantity(1);
        product.setWeight(3);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getProducts()).thenReturn(productList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/products");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.view().name("uproduct"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("uproduct"));
    }
    @Test
    void testNewUseRegister() throws Exception {
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
        when(userService.addUser(Mockito.<User>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/newuserregister");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
    @Test
    void testTest() {
        UserController userController = new UserController();
        ConcurrentModel model = new ConcurrentModel();
        String actualTestResult = userController.Test(model);
        Object getResult = model.get("f");
        assertEquals("abc", ((List<String>) getResult).get(1));
        assertEquals("test", actualTestResult);
        assertEquals("xyz", ((List<String>) getResult).get(0));
        assertEquals(2, ((Collection<String>) getResult).size());
    }

    @Test
    void testUpdateUserProfile() throws Exception {
        when(jdbcTemplate.update(Mockito.<String>any(), (Object[]) any())).thenReturn(1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile/update")
                .param("address", "foo")
                .param("email", "foo")
                .param("password", "foo")
                .param("userid", "foo")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("successMessage"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/user-profile"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user-profile?successMessage=Profile+updated+successfully"));
    }
    @Test
    void testUpdateUserProfile2() throws Exception {
        when(jdbcTemplate.update(Mockito.<String>any(), (Object[]) any())).thenReturn(0);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/profile/update")
                .param("address", "foo")
                .param("email", "foo")
                .param("password", "foo")
                .param("userid", "foo")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("/user-profile"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/user-profile"));
    }
    @Test
    void testUserlogin() throws Exception {
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
        when(userService.checkLogin(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/userloginvalidate")
                .param("password", "foo")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("msg"))
                .andExpect(MockMvcResultMatchers.view().name("userLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userLogin"));
    }
    @Test
    void testUserlogin2() throws Exception {
        when(productService.getProducts()).thenReturn(new ArrayList<>());
        doNothing().when(sessionData).setCurrentUser(Mockito.<User>any());

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
        user.setUsername("foo");
        when(userService.checkLogin(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/userloginvalidate")
                .param("password", "foo")
                .param("username", "foo");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("msg", "user"))
                .andExpect(MockMvcResultMatchers.view().name("uproduct"));
        ResultActions resultActions2 = resultActions.andExpect(MockMvcResultMatchers.cookie().value("username", "foo"));
        ResultActions resultActions3 = resultActions2.andExpect(MockMvcResultMatchers.cookie().secure("username", false));
        ResultActions resultActions4 = resultActions3.andExpect(MockMvcResultMatchers.cookie().httpOnly("username", false));
        resultActions4.andExpect(MockMvcResultMatchers.cookie().maxAge("username", -1))
                .andExpect(MockMvcResultMatchers.forwardedUrl("uproduct"));
    }
    @Test
    void testUserlogin3() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("?");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setId(1);
        product.setImage("?");
        product.setName("?");
        product.setPrice(2);
        product.setQuantity(2);
        product.setWeight(3);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getProducts()).thenReturn(productList);
        doNothing().when(sessionData).setCurrentUser(Mockito.<User>any());

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
        user.setUsername("foo");
        when(userService.checkLogin(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/userloginvalidate")
                .param("password", "foo")
                .param("username", "foo");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products", "user"))
                .andExpect(MockMvcResultMatchers.view().name("uproduct"));
        ResultActions resultActions2 = resultActions.andExpect(MockMvcResultMatchers.cookie().value("username", "foo"));
        ResultActions resultActions3 = resultActions2.andExpect(MockMvcResultMatchers.cookie().secure("username", false));
        ResultActions resultActions4 = resultActions3.andExpect(MockMvcResultMatchers.cookie().httpOnly("username", false));
        resultActions4.andExpect(MockMvcResultMatchers.cookie().maxAge("username", -1))
                .andExpect(MockMvcResultMatchers.forwardedUrl("uproduct"));
    }
    @Test
    void testUserlogin4() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("userLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userLogin"));
    }
    @Test
    void testUserlogin5() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("userLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userLogin"));
    }
}
