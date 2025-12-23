# CH2Commerce

스프링 부트캠프 커머스 과제

```mermaid
classDiagram
    direction TB
    class Product {
        -static int productNum = 0;
        -int num
        -String name
        -int price
        -String category
        -String description
        -int status
        -int stock
        
        +Product(String name, int price, String category, String description, int status, int stock)
        
        +getNum: int
        +getName: String
        +getPrice: int
        +getCategory: String
        +getDescription: String
        +getStatus: int
        +getStock: int
        
        +plusStock(int count): void
        +minusStock(int count): void
        
        +toString(): String
    }
    class Category {
        -int num
        -String name
        -List<Product> products
        
        +Category(String name)
        
        +toString(): String
    }
    class Customer {
        -int num
        -String name
        -List<Order> orders
        -String email
        -String rating
        
        +Customer(String name, String email)
        
        
        +getName() : String
        +getEmail() : String
        +getRating() : String
        +getOrderList() : List<Order>


    }
    class Order {
        -int num
        -String status
        -List<Product> products
        -Customer customer
        -int totalPrice
        -Customer customer
        
        +toOrder(Product p): void
        +printOrderList(): void
        
    }
    class ShoppingCart {
        -int num
        -Customer customer
        -List<Product> products
        -int totalPrice
        
        +printCart(): void
        +putProductToCart(Product p): void
        +deleteProduct(Product p): void
    }
    class CommerceSystem {
        -List<Category> categories
        -List<Customer> customers
        -List<Order> orders
        -List<ShoppingCart> shoppingCarts
        
        +start() : void

        +getProductList: List<String>
        +getCategoryList: List<String>
        
        +addCategory(Category c): void
        +addCustomer(Customer c): void
        +addOrder(Order o): void
        +addShoppingCart(ShoppingCart sc): void
    }
    class Administrator {
        -CommerceSystem system
        +addProduct(Product p): void
        +modifyProduct(Product p): void
        +deleteProduct(Product p): void
 }
    
class Printer {
    -List<Product> list
    +printList(List<Product> list): void
}



```