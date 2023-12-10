package com.jtspringproject.JtSpringProject.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.categoryService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private categoryService categoryService;

    @MockBean
    private productService productService;

    @MockBean
    private userService userService;

    @Test
    void testGetcategory() {
        assertTrue((new AdminController()).getcategory().isReference());
    }

    @Test
    void testAddCategory() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.addCategory(Mockito.<String>any())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/categories")
                .param("categoryname", "foo");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/categories"));
    }

    @Test
    void testAddCategory2() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("foo");
        when(categoryService.addCategory(Mockito.<String>any())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/categories")
                .param("categoryname", "foo");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/categories"));
    }

    @Test
    void testAddCategory3() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.addCategory(Mockito.<String>any())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/categories")
                .param("categoryname", "");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/categories"));
    }

    @Test
    void testUpdateCategory() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.updateCategory(anyInt(), Mockito.<String>any())).thenReturn(category);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/categories/update");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("categoryid", String.valueOf(1))
                .param("categoryname", "foo");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/categories"));
    }

    @Test
    void testUpdateCategory2() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.getCategories()).thenReturn(new ArrayList<>());
        when(categoryService.updateCategory(anyInt(), Mockito.<String>any())).thenReturn(category);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/categories/update");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("categoryid", String.valueOf(1))
                .param("categoryname", "");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/categories"));
    }

    @Test
    void testGetproduct() {
        assertTrue((new AdminController()).getproduct().isReference());
    }

    @Test
    void testAddProduct3() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        when(categoryService.getCategory(anyInt())).thenReturn(category);

        Category category2 = new Category();
        category2.setId(1);
        category2.setName("Name");

        Product product = new Product();
        product.setCategory(category2);
        product.setDescription("The characteristics of someone or something");
        product.setId(1);
        product.setImage("Image");
        product.setName("Name");
        product.setPrice(1);
        product.setQuantity(1);
        product.setWeight(3);
        when(productService.addProduct(Mockito.<Product>any())).thenReturn(product);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/admin/products/add");
        MockHttpServletRequestBuilder paramResult = postResult.param("categoryid", String.valueOf(1))
                .param("description", "foo")
                .param("name", "foo");
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("price", String.valueOf(1))
                .param("productImage", "foo");
        MockHttpServletRequestBuilder paramResult3 = paramResult2.param("quantity", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult3.param("weight", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/products"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));
    }

    @Test
    void testAddProduct() throws Exception {
        when(categoryService.getCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/products/add");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
                .andExpect(MockMvcResultMatchers.view().name("productsAdd"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("productsAdd"));
    }

    @Test
    void testAddProduct2() throws Exception {
        when(categoryService.getCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/products/add");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
                .andExpect(MockMvcResultMatchers.view().name("productsAdd"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("productsAdd"));
    }

    @Test
    void testAdminlog() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/loginvalidate");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("adminlogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("adminlogin"));
    }

    @Test
    void testAdminlog2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/loginvalidate");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("adminlogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("adminlogin"));
    }

    @Test
    void testAdminlogin() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/login");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("adminlogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("adminlogin"));
    }

    @Test
    void testAdminlogin2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/login");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("adminlogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("adminlogin"));
    }

    @Test
    void testAdminlogin3() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/loginvalidate")
                .param("password", "foo")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("msg"))
                .andExpect(MockMvcResultMatchers.view().name("adminlogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("adminlogin"));
    }

    @Test
    void testAdminlogin4() throws Exception {
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
        user.setRole("ROLE_ADMIN");
        user.setUsername("janedoe");
        when(userService.checkLogin(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/loginvalidate")
                .param("password", "foo")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("admin"))
                .andExpect(MockMvcResultMatchers.view().name("adminHome"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("adminHome"));
    }

    @Test
    void testGetCustomerDetail2() throws Exception {
        when(userService.getUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/customers");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("customers"))
                .andExpect(MockMvcResultMatchers.view().name("displayCustomers"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("displayCustomers"));
    }

    @Test
    void testIndex() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/index");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("userLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userLogin"));
    }

    @Test
    void testIndex2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/index");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("userLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userLogin"));
    }

    @Test
    void testPostproduct() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/products");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/categories"));
    }

    @Test
    void testPostproduct2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/products");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/categories"));
    }

    @Test
    void testProfileDisplay() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/user-profile");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("updateProfile"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("updateProfile"));
    }

    @Test
    void testProfileDisplay2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/user-profile");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("updateProfile"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("updateProfile"));
    }

    @Test
    void testRemoveCategoryDb() throws Exception {
        when(categoryService.deleteCategory(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/categories/delete");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/categories"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/categories"));
    }

    @Test
    void testRemoveCustomer() throws Exception {
        when(userService.deleteUser(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/customers/delete");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/customers"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/customers"));
    }

    @Test
    void testRemoveProduct() throws Exception {
        when(productService.deleteProduct(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin/products/delete");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/products"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));
    }

    @Test
    void testReturnIndex() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("userLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userLogin"));
    }

    @Test
    void testReturnIndex2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("userLogin"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("userLogin"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/admin/products/update/{id}", 1);
        MockHttpServletRequestBuilder paramResult = postResult.param("categoryid", String.valueOf(1))
                .param("description", "foo")
                .param("name", "foo");
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("price", String.valueOf(1))
                .param("productImage", "foo");
        MockHttpServletRequestBuilder paramResult3 = paramResult2.param("quantity", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult3.param("weight", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/admin/products"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/products"));
    }

    @Test
    void testUpdateproduct() throws Exception {
        when(categoryService.getCategories()).thenReturn(new ArrayList<>());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/products/update/{id}", 1);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories", "product"))
                .andExpect(MockMvcResultMatchers.view().name("productsUpdate"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("productsUpdate"));
    }
}
