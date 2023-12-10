package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jtspringproject.JtSpringProject.services.cartService;
import com.jtspringproject.JtSpringProject.utils.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.cartService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController{

	@Autowired
	private userService userService;

	@Autowired
	private productService productService;
	@Autowired
	private cartService cartService;

	@Autowired
	private SessionData sessionData; // to maintain details of current loggedIn User

	@GetMapping("/register")
	public String registerUser()
	{
		return "register";
	}

	@GetMapping("/adminhome")
	public String adminHome()
	{
		return "adminHome";
	}

	@GetMapping("/user-profile")
	public ModelAndView getUserProfile(){
		ModelAndView modelAndView = new ModelAndView("updateProfile");
		modelAndView.addObject("user",sessionData.getCurrentUser());
		return modelAndView;
	}
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	public ModelAndView updateUserProfile(
			@RequestParam("userid") String userid,
			@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("address") String address,
			RedirectAttributes redirectAttributes) {

        int rowCount = jdbcTemplate.update(
                "UPDATE customer SET username=?, email=?, password=?, address=? WHERE id= 2",
                username, email, password, address);

        if (rowCount > 0) {
            ModelAndView mv = new ModelAndView("redirect:/user-profile");
			redirectAttributes.addAttribute("successMessage", "Profile updated successfully");
			return mv;
        }

        ModelAndView mv = new ModelAndView("/user-profile");
		redirectAttributes.addAttribute("errorMessage", "Failed to update Profile");

		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView getLogin(){
		ModelAndView modelAndView = new ModelAndView("userLogin");
		modelAndView.addObject("user",sessionData.getCurrentUser());
		return modelAndView;
	}
	@GetMapping("/cart")
	public ModelAndView getCart(){
		ModelAndView modelAndView = new ModelAndView("cartproduct");
		modelAndView.addObject("user",sessionData.getCurrentUser());
		return modelAndView;
	}
	@GetMapping("/cart/delete")
	public ModelAndView deleteCartProduct(){
		ModelAndView modelAndView = new ModelAndView("cartproduct");
		modelAndView.addObject("user",sessionData.getCurrentUser());
		return modelAndView;
	}

	@GetMapping("/buy")
	public String buy()
	{
		return "buy";
	}


	@GetMapping("/")
	public String userlogin(Model model) {

		return "userLogin";
	}
	@RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)//Modified
	public ModelAndView userlogin( @RequestParam("username") String username, @RequestParam("password") String pass,Model model,HttpServletResponse res,HttpSession session) {

		System.out.println(pass);
		User u = this.userService.checkLogin(username, pass);
		System.out.println(u.getUsername());
		if(u.getUsername().equals(username)) {

			res.addCookie(new Cookie("username", u.getUsername()));
			// Add the user to the session
			session.setAttribute("user", u);
			sessionData.setCurrentUser(u);
			ModelAndView mView  = new ModelAndView("uproduct");
			mView.addObject("user", u);
			List<Product> products = this.productService.getProducts();

			if (products.isEmpty()) {
				mView.addObject("msg", "No products are available");
			} else {
				mView.addObject("products", products);
			}
			System.out.println("Model: "+mView.toString());
			return mView;

		} else {
			ModelAndView mView = new ModelAndView("userLogin");
			mView.addObject("msg", "Please enter correct email and password");
			return mView;
		}

	}


	@GetMapping("/user/products")
	public ModelAndView getproduct() {

		ModelAndView mView = new ModelAndView("uproduct");

		List<Product> products = this.productService.getProducts();

		if(products.isEmpty()) {
			mView.addObject("msg","No products are available");
		}else {
			mView.addObject("products",products);
		}

		return mView;
	}

	@Transactional
	@RequestMapping(value="/products/addtocart",method = RequestMethod.POST)//New added
	public String addToCart(@RequestParam("productId") int productId, @RequestParam("userId") int userId, HttpSession session, RedirectAttributes redirectAttributes) {
		System.out.println("UserId:");
		Product product = productService.getProduct(productId);
		User user = (User) session.getAttribute("user");
		System.out.println("UserId:"+userId);
		Cart c = cartService.getCartByUserId(user.getId());
		System.out.println("user.getId:"+user.getId());
		if (c == null) {
			c = new Cart();
			c.setCustomer(user);
			cartService.addCart(c);
		}
		c.addProduct(product);
		cartService.updateCart(c);
		redirectAttributes.addAttribute("successMessage", "Product added to cart successfully");
		return "redirect:/user/products";
	}
	@RequestMapping(value = "newuserregister", method = RequestMethod.POST)
	public String newUseRegister(@ModelAttribute User user)
	{

		System.out.println(user.getEmail());
		user.setRole("ROLE_NORMAL");
		this.userService.addUser(user);

		return "redirect:/";
	}
	//for Learning purpose of model
	@GetMapping("/test")
	public String Test(Model model)
	{
		System.out.println("test page");
		model.addAttribute("author","jay gajera");
		model.addAttribute("id",40);

		List<String> friends = new ArrayList<String>();
		model.addAttribute("f",friends);
		friends.add("xyz");
		friends.add("abc");

		return "test";
	}

	// for learning purpose of model and view ( how data is pass to view)

	@GetMapping("/test2")
	public ModelAndView Test2()
	{
		System.out.println("test page");
		//create modelandview object
		ModelAndView mv=new ModelAndView();
		mv.addObject("name","jay gajera 17");
		mv.addObject("id",40);
		mv.setViewName("test2");

		List<Integer> list=new ArrayList<Integer>();
		list.add(10);
		list.add(25);
		mv.addObject("marks",list);
		return mv;


	}

//	@GetMapping("carts")
//	public ModelAndView  getCartDetail()
//	{
//		ModelAndView mv= new ModelAndView();
//		List<Cart>carts = cartService.getCarts();
//	}

}