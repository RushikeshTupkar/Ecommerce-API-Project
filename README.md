# Ecommerce Api Project 
## Frameworks and language used:
-  Spring Boot
-  Java
- SQL database
## Data Flow


* **Model** :
  There are Four Model class in this application.
    * User Model Class.
    * AddressTable Model Class.
    * Product Model Class.
    * OrderTable Model Class. 

* **Controller** :

  There are Four Controller class in this application.

    * User Controller Class.
    * Address Controller Class.
    * Product Controller Class.
    * Order Controller Class.


**Functions/Endpoints used in User Controller Class.**
- To add User data :
- http://localhost:8080/api/v1/user/addUser


- To get all User's information:
- http://localhost:8080/api/v1/user/getAllUsers


- To update User's details:
- http://localhost:8080/api/v1/user/updateUser_details


- To delete User by id:
- http://localhost:8080/api/v1/user/deleteUser_with_user_id?user_id=2


- To get User by id:
- http://localhost:8080/api/v1/user/getUser_By_Id/2



**Functions/Endpoints used in Address Controller Class.**

- To add Address:
- http://localhost:8080/api/v1/address/addAddress


- To get all addresses associated with perticular User by user_id:
- http://localhost:8080/api/v1/address/get_all_addresses_of_user/2


- To get all addresses:
- http://localhost:8080/api/v1/address/getALl_addresses


- To get address by address_id:
- http://localhost:8080/api/v1/address/getAddres_By_address_id/2

**Functions/Endpoints used in Product Controller Class.**

- To add product:
- http://localhost:8080/api/v1/product/addProduct

- To get all products:
- http://localhost:8080/api/v1/product/getAll_products

- To delete product with product_id:
- http://localhost:8080/api/v1/product/deleteProduct_with_product_id/1

- To update product details:
- http://localhost:8080/api/v1/product/update_product_details

- To get product with product_id
- http://localhost:8080/api/v1/product/getProduct_By_product_id/1

- To get products of desires category:
- http://localhost:8080/api/v1/product/getProduct_based_on_category?category=XUV



**Functions/Endpoints used in Order Controller Class.**

- To place/add Order:
- http://localhost:8080/api/v1/order/placeOrder

- To get all Orders;
- http://localhost:8080/api/v1/order/findAllOrders

- To get Order by order_id:
- http://localhost:8080/api/v1/order/find_order_by_order_id/3

- To delete Order by order_id:
- http://localhost:8080/api/v1/address/delete_addredd_by_address_id/2




* **Service** :

    * In this application there are Four service class .
        * User Service Class
        * Address Service Class
        * Product Service Class
        * Order Service Class


* **Util/Validation** :
    * In this application there are Four Validation class .
      * User Validation Class
      * Address Validation Class
      * Product Validation Class
      * Order Validation Class




* **Repository**

    * In this application there Four Repository class.
        * IUserRepository
        * IAddressRepository
        *  IProductRepository 
        * IOrderTableRepository

* **Database**

    * I have used SQL Database to store the data.

## Project Summary

In this application by using various RestApi we can add User, we can associate multiple
addresses to a perticular User.
We can add multiple peoducts and can perform CRUD operations.
we can add Orders based on unique  User's user_id, Address's address_id and Product's produst_id.
We can validate all details of User like email, phone_number etc.
we fetch various data using different ewndpoints.
We can visualize data on SQL workbench.




  













  
