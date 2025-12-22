import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Order {
    private static int orderNum = 0;
    private int num;
    private Customer customer;
    private ShoppingCart cart;
    private Map<Product, Integer> products;
    private int totalPrice;
    private boolean status; //주문완료 전 0, 주문완료 1


    Order(Customer customer, ShoppingCart cart) { // 장바구니에서 받아서만 주문하도록 함.
        this.num = ++orderNum;
        this.customer = customer;
        this.cart = cart;
        this.products = new HashMap<>();
        this.products.putAll(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = false;
    }

    public int getNum() {
        return this.num;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Product, Integer> getProducts() {
        return this.products;
    }


    public void toOrder() {
        //주문시 주문완료 상태로 변경, 주문상품의 재고 줄이고, 카트 비우고, 가격 계산하기
        int totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : this.getProducts().entrySet()) { // getProducts: Map<Product, Integer> 리턴하는 함수
            Product product = entry.getKey();
            int count = entry.getValue();
            product.minusStock(count); // 재고 줄이고
            totalPrice += product.getPrice() * count; // 가격 계산하고
        }

        //주문완료 상태로 변경
        this.setStatus(true);

        //카트 비우고
        Iterator<Map.Entry<Product, Integer>> it = cart.getProducts().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Product, Integer> entry = it.next();
            if (entry.getValue() == 0) {
                it.remove();   //
            }


        }


        cart.setTotalPrice(totalPrice);
        System.out.println("주문이 완료되었습니다!" + "총 금액: " + cart.getTotalPrice() + "원, 주문번호: " + this.getNum());
        cart = null;
    }

    public void cancelOrder(int num) {

        //주문시 주문전 상태로 변경, 주문상품의 재고 늘리고, 카트는 그대로 빈칸으로 두고, 가격 계산하기

        // 주문번호 검증
        if (this.num != num) {
            System.out.println("주문번호가 일치하지 않습니다.");
            return;
        }

        // 이미 취소된 주문 방지
        if (!this.status) {
            System.out.println("이미 취소된 주문입니다.");
            return;
        }

        // 주문 상품을 카트로 이동 + 재고 복구
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();

            product.plusStock(count);// 재고 복구
            cart = new ShoppingCart(customer);
            cart.putProductToCart(product, count); // 카트로 복귀
        }

        // 주문 객체(this) 초기화
        this.products.clear();
        this.totalPrice = 0;
        this.status = false;

        System.out.println("주문이 취소되었습니다. 주문번호: " + num);
    }

    public void clear(){
        this.setTotalPrice(0);
        this.cart.clear();
        this.products.clear();
        this.setStatus(false);
    }

    public boolean isEmpty(){
        return products.isEmpty();
    }
}
