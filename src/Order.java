import java.util.List;
import java.util.Map;

public class Order {
    private static int orderNum = 0;
    private int num;
    private Customer customer;
    private ShoppingCart cart;
    private int totalPrice;
    private boolean status; //주문완료 전 0, 주문완료 1


    Order(Customer customer, ShoppingCart cart) { // 장바구니에서 받아서만 주문하도록 함.
        this.num = ++orderNum;
        this.customer = customer;
        this.cart = cart;
        this.totalPrice = cart.getTotalPrice();
        this.status = false;
    }

    public int getNum() {
        return this.num;
    }

    public void toOrder(Map<Product, Integer> cart) {
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) { // getProducts: 카트의 Map<Product, Integer> 리턴하는 함수
            Product product = entry.getKey();
            int count = entry.getValue();

            product.minusStock(count);
        }
    }


}
