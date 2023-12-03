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
  https://e-commerce14.odoo.com/web#cids=1&action=menu
  <img width="926" alt="image" src="https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102314527/a067b457-4ab0-431c-9689-dc87d5d502b7">
- Logout button implementation on Category page, which logouts the admin and navigates to login page
  https://e-commerce14.odoo.com/web#action=369&model=product.public.category&view_type=list&menu_id=182&cids=1
  <img width="926" alt="image" src="https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102314527/ad6f01fc-95e4-4958-aa48-37444c774569">
- Logout button implementation on Add New Product page, which logouts the admin and navigates to login page
  https://e-commerce14.odoo.com/web#id=4&menu_id=182&cids=1&action=368&model=product.template&view_type=form
  <img width="922" alt="image" src="https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102314527/ce85f084-81d1-4ec5-ad25-8292a62ebb8f">
- Logout button implementation on Products page, which logouts the admin and navigates to login page
  https://e-commerce14.odoo.com/web#action=368&model=product.template&view_type=kanban&menu_id=182&cids=1
  <img width="922" alt="image" src="https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102314527/9c15a37b-052c-4299-848b-9e9f6bcb2e3e">
- Logout button implementation on Customers page, which logouts the admin and navigates to login page<br/>
	https://pk.sapphireonline.pk/account/login?return_url=%2Faccount
![alt text](https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102382100/86e6bd72-5459-458a-8205-22dca12773ae)
- Homepage button implementation on Admin Dashboard page, which navigates admin to homepage
  https://e-commerce14.odoo.com/web#action=370&model=sale.order&view_type=list&menu_id=182&cids=1
  <img width="360" alt="image" src="https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102314527/88d80cb1-b82f-477f-9063-dbeaabf6c29c">
- Homepage button implementation on Category page, which navigates admin to homepage
  https://e-commerce14.odoo.com/web#action=369&model=product.public.category&view_type=list&menu_id=182&cids=1
  <img width="930" alt="image" src="https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102314527/02fb77b1-5333-4b96-aba6-cbbf2fe36bbb">
- Homepage button implementation on Products page, which navigates admin to homepage
  [https://e-commerce14.odoo.com/web#action=368&model=product.template&view_type=kanban&menu_id=182&cids=1](https://e-commerce14.odoo.com/web#action=368&model=product.template&view_type=kanban&menu_id=182&cids=1)
  <img width="921" alt="image" src="https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102314527/7dc5ff86-ab2e-4fd3-b2b5-3a6b44b5810b">
- Homepage button implementation on Add New Product page, which navigates admin to homepage
  https://e-commerce14.odoo.com/web#action=368&model=product.template&view_type=kanban&menu_id=182&cids=1
  <img width="927" alt="image" src="https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102314527/962cc69d-06c4-490b-8435-2e0f5f0028a6">
- Homepage button implementation on Customers page, which navigates admin to homepage<br/>
https://e-commerce14.odoo.com/web#action=56&model=res.partner&view_type=kanban&menu_id=182&cids=1
<img width="722" alt="image" src="https://github.com/Areeba-Tariq/Ecommerce-App-Springboot/assets/102314527/78da5c54-0fe9-43f0-98f3-7514b9e1cfca"><br/>
- Update button on Products page, which allows admin to update the product details<br/>
Junit tests:
Admin Controller Tests:
The AdminControllerTest class focuses on testing the behavior of the `AdminController` class. It covers scenarios such as admin login validation, category addition, deletion, and updating functionalities, and the addition, updating, and removal of products.

User Controller Tests:
In the UserControllerTest class, the testing revolves around user-related functionalities. It verifies user login validation, product retrieval, and addition to the cart. Additionally, user registration and profile display functionalities are tested.

Cart Dao Tests:
The CartDaoTest class ensures the correctness of the CRUD operations in the `cartDao`. It includes tests for adding a cart, retrieving all carts, updating a cart, and deleting a cart. Also, a specific test is conducted to fetch a cart by user ID.

Category Dao Tests:
The CategoryDaoTest class focuses on testing the `categoryDao`. It validates the addition, retrieval, update, and deletion of categories. Each operation is independently tested to ensure the proper functioning of the DAO.

Product Dao Tests:
The ProductDaoTest class verifies the correctness of operations in the `productDao`. The tests include getting all products, adding a new product, retrieving a product by ID, updating a product, and deleting a product.

Cart Service Tests:
In the CartServiceTest class, tests are conducted to validate the operations performed by the `cartService`. This includes adding a cart, getting all carts, updating a cart, deleting a cart, and fetching a cart by user ID.

Category Service Tests:
The CategoryServiceTest class ensures the proper functioning of the `categoryService`. It covers tests for adding a category, getting all categories, deleting a category, updating a category, and retrieving a category by ID.
Each test suite aims to validate the correct behavior of the corresponding component, ensuring that the application functions as expected under various scenarios.