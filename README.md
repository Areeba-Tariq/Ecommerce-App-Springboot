# SQE Term Project

# Ecommerce-App-Springboot

## Deliverable 1
- Edit run configurations
- Change working directory to ```$MODULE_WORKING_DIR$``` macro
- executeQuery syntax error in AdminController.java
- Add sql username and password in application.properties
- Added some imports and libraries that were used in JtSpringProjectApplication.java, AdminController,java, UserController.java, cartDao.java, categoryDao.java, productDao.java, and userDao.java

## Deliverable 2
### Issues 
- The website allows usernames, that contain only numeric values or start with numbers, to register.
    e.g., username = “1234”
- The website allows passwords that contain less than 4 digits.
    e.g., password = “123”
- The website allows registration with the wrong email format.
    e.g., email = “example”
- The website does not display an “Invalid login credentials” message on the login page.
- The website does not display a “No products available” message when there are no products added to the database.
- The website allows the admin to update the name category name to [no name]. Allows update when the category field is empty.
    e.g., category= “”
- Delete button on Category page navigates to whitelabel error page, the category is deleted after refreshing the category page
- The website does not display a “Please fill out this field” message on empty category field while adding a new product.
- The website does not display a “Wrong link format” message rather adds the product with no image, on entering a wrong link format while adding a new product.

### Missing Features
- Add to Cart button implementation on Homepage, which allows user to add a product to cart<br/>
https://pk.sapphireonline.pk/products/sfde-lx23v8-6-1
  ![alt text](https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102382100/de5424ef-fff8-42d9-9d1d-47b2167f039d)
- Cart button implementation on Homepage, which allows user to view cart<br/>
	https://pk.sapphireonline.pk/car
![alt text](https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102382100/edb02849-a8d7-44de-afe6-46f59bd48250)
- Profile button implementation on Homepage, which allows user to view and update profile<br/>
	https://pk.sapphireonline.pk/account
![alt text](https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102382100/34744f4c-e2f4-4d0f-9236-d5453aadc16f)
- Logout button implementation on Homepage, which logouts the user and navigates to login page
- Logout button implementation on Category page, which logouts the admin and navigates to login page
- Logout button implementation on Add New Product page, which logouts the admin and navigates to login page
- Logout button implementation on Products page, which logouts the admin and navigates to login page
- Logout button implementation on Customers page, which logouts the admin and navigates to login page<br/>
	https://pk.sapphireonline.pk/account/login?return_url=%2Faccount
![alt text](https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102382100/86e6bd72-5459-458a-8205-22dca12773ae)
- Homepage button implementation on Admin Dashboard page, which navigates admin to homepage
- Homepage button implementation on Category page, which navigates admin to homepage
- Homepage button implementation on Products page, which navigates admin to homepage
- Homepage button implementation on Add New Product page, which navigates admin to homepage
- Homepage button implementation on Customers page, which navigates admin to homepage<br/>

- Update button on Products page, which allows admin to update the product details<br/>
