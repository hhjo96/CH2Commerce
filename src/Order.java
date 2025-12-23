import java.util.*;

public class Order {
    private static int orderNum = 0;
    private int num;
    private Customer customer;
    private ShoppingCart cart;
    private Map<Product, Integer> products;
    private double totalPrice;
    private String status; //"done": 주문 끝났음, "yet": 아직 주문 완료 안했음, "cancel": 취소함


    Order(Customer customer, ShoppingCart cart) { // 장바구니에서 받아서만 주문하도록 함.
        this.num = ++orderNum;
        this.customer = customer;
        this.cart = cart;
        this.products = new HashMap<>();
        this.products.putAll(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = "yet";
    }

    public int getNum() {
        return this.num;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        if(status.equals("yet") || status.equals("done") || status.equals("cancel")){
            this.status = status;
        } else {
            this.status = "yet";
        }
    }
    public double getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Product, Integer> getProducts() {
        return this.products;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void toOrder() {
        //주문시 주문완료 상태로 변경, 주문상품의 재고 줄이고, 카트 비우고, 가격 계산하기
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : this.getProducts().entrySet()) { // getProducts: Map<Product, Integer> 리턴하는 함수
            Product product = entry.getKey();
            int count = entry.getValue();
            product.minusStock(count); // 재고 줄이고
            totalPrice = totalPrice + (product.getPrice() * count) * (1-(this.customer.getLevel().getLevel()/100.0));// 가격 계산하고
            this.totalPrice = totalPrice;
        }

        //주문완료 상태로 변경
        this.setStatus("done");

        //카트 비우고
        cart.getProducts().clear();

        cart.setTotalPrice(totalPrice);
        System.out.println("주문이 완료되었습니다! " +
                customer.getLevel() + "등급 할인율: " +
                customer.getLevel().getLevel() + "%, 할인 후 가격: " +
                cart.getTotalPrice() + "원, 주문번호: " + this.getNum());

        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            Product product = entry.getKey();
            System.out.println(product + " 재고가 " + product.getStock()+ "개로 업데이트되었습니다.");
        }

        cart = null;
    }

    public void cancelOrder(int num, Scanner sc) {


        cart = new ShoppingCart(customer);
        //주문시 주문전 상태로 변경, 주문상품의 재고 늘리고, 카트는 그대로 빈칸으로 두고, 가격 계산하기

        // 주문번호 검증
        if (this.num != num) {
            System.out.println("주문번호가 일치하지 않습니다.");
            return;
        }

        // 이미 취소된 주문 방지
        if (this.status.equals("cancel")) {
            System.out.println("이미 취소된 주문입니다.");
            return;
        }

        System.out.println("정말 취소하시겠습니까?(y/n)");
        String answer = sc.nextLine();

        if(answer.equalsIgnoreCase("y")) { //y 입력한경우 취소
            // 주문 상품을 카트로 이동 + 재고 복구
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int count = entry.getValue();

                product.plusStock(count);// 재고 복구
                cart.putProductToCart(product, count); // 카트로 복귀
            }

            // 주문 객체(this) 초기화
            this.products.clear();
            this.totalPrice = 0;
            this.status = "cancel";

            System.out.println("주문이 취소되었습니다. 주문번호: " + num);
            this.clear();

        } else {
            System.out.println("주문을 취소하지 않고 돌아갑니다.");
        }


    }

    public void clear(){
        if(cart != null) {
            this.setTotalPrice(0);
            this.cart.clear();
            this.products.clear();
        }
    }

    public boolean isEmpty(){
        return products.isEmpty();
    }
}
