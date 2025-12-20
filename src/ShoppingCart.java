import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

       private int num;
       private Customer customer;
       private Map<Product, Integer> products; // 상품, 수량
       private int totalPrice;

       ShoppingCart(Customer customer) {
           this.customer = customer;
           this.num = customer.getNum();
           this.totalPrice = 0;
       }
       public Map<Product, Integer> getProducts() {
           return this.products;
       }

        public void printCart() {
           System.out.println(products);
        }
        public void putProductToCart(Product p, int count) {//장바구니에 count 개수만큼 담기
            this.products.put(p, count);
            this.totalPrice += p.getPrice() * count;
        }
        public void deleteProduct(Product p) {//장바구니의 해당상품 전체삭제
            this.products.remove(p);
        }
        public Order cartToOrder() {
           return new Order(this.customer, this);
        }
        public int getTotalPrice() {
            return this.totalPrice;
        }
}
