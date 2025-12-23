import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

       private int num;
       private Customer customer;
       private Map<Product, Integer> products; // 상품, 수량
       private double totalPrice;

       ShoppingCart(Customer customer) {
           this.customer = customer;
           this.num = customer.getNum();
           this.totalPrice = 0;
           this.products = new HashMap<>();
       }
       public Map<Product, Integer> getProducts() {
           return this.products;
       }

       public void setTotalPrice(double totalPrice) {
           this.totalPrice = totalPrice;
       }

        public void printCart() {
            for(Map.Entry<Product, Integer> products : products.entrySet()) {
                System.out.println(products.getKey() + " 주문 수량: " + products.getValue());
            }
        }
        public void putProductToCart(Product p, int count) {
           //장바구니에 count 개수만큼 담기
            // 음수 처리는 input 함수에서 처리하므로 p가 삭제된 상품인 경우만 확인
            if(p.getStatus()) {
                this.products.put(p, this.products.getOrDefault(p, 0) + count);
                this.totalPrice += p.getPrice() * count;
            }
        }
        public void deleteProduct(Product p) {//장바구니의 해당상품 전체삭제
            this.products.remove(p);
        }
        public void deleteProduct(String name) {
            boolean removed = products.entrySet().removeIf(entry -> entry.getKey().getName().contains(name));

            if (!removed) {
                System.out.println("해당 이름이 포함된 상품이 존재하지 않습니다.");
            } else {
                System.out.println("상품이 삭제되었습니다.");
            }
        }


        public Order cartToOrder() {
           return new Order(this.customer, this);
        }
        public double getTotalPrice() {
            return this.totalPrice;
        }
        public void clear(){
           this.products.clear();
           this.totalPrice = 0;
        }
        public boolean isEmpty(){
           return this.products.isEmpty();
        }
}
