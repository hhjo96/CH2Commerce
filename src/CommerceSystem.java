
import javax.sound.midi.Soundbank;
import java.util.*;


public class CommerceSystem {

    Scanner sc = new Scanner(System.in);
    Map<Integer, Category> categoryMap = new HashMap<>();
    Map<Integer, Product> productMap = new HashMap<>();
    Customer customer;
    ShoppingCart cart;
    Order order;

    public CommerceSystem(Customer customer) {
        this.customer = customer;
        this.cart = null;
        this.order = null;

    }

    public int intInputCheck(){ // 양수 숫자인지 체크
        int answer = 0;
        while(true) {
            try{
                answer = sc.nextInt();
                sc.nextLine();
                if(answer < 0) {
                    continue;
                }
                return answer;
            } catch (InputMismatchException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
                sc.nextLine();
            }
        }
    }

    public void start() {

        while (true) {
            //카테고리 목록 보여주기
            int answer1;

            printCategoryList();
            //장바구니에 상품이 들어있으면 주문관리 출력, 없으면 안출력, 예외처리
            if (cart != null && !cart.isEmpty()) { // 카트내역이 있을 때
                Printer.printOrderManage(); //'주문 관리'
                Printer.printCartList(); // 4. 장바구니 확인
            }
            //주문내역이 있을 때
            if(order != null && !order.isEmpty()){
                //[ 주문 관리] 는 한번만 출력하기 위해 검사
                if(cart == null || cart.isEmpty()) {
                    Printer.printOrderManage(); //'주문 관리'
                }
                Printer.printCancelOrder();// 5. 주문 취소
            }

            answer1 = intInputCheck();

            switch (answer1) {
                case 0://0 종료
                    Printer.printEnd();
                    return;
                case 4:
                    if ((cart.isEmpty()) ) { // 장바구니 상품 없을 때
                        System.out.println("올바른 번호를 입력해주세요. 1");
                        break;
                    } else {// 장바구니 상품이 있을 때 장바구니 확인
                        printCart();
                        Printer.printOrderOrBack(); // 주문할지 뒤로가기할지 물어보기
                        int answer2 = intInputCheck();

                        if (answer2 == 1) {//1 주문확정
                            order = new Order(customer, cart);
                            //System.out.println("디버그: " + cart.getProducts().toString());
                            order.toOrder();
                            for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
                                Product product = entry.getKey();
                                System.out.println(product + " 재고가 " + product.getStock()+ "개로 업데이트되었습니다.");
                            }
                            cart.clear();
                            continue;
                        } else { // 2 메인으로 돌아가기
                            System.out.println("올바른 번호를 입력해주세요. 1.5");
                            break;
                        }
                    }
                case 5: // 주문 취소
                    if(order.isEmpty()) {
                        System.out.println("올바른 번호를 입력해주세요. 2");
                        break;
                    }
                    System.out.print("취소할 주문의 주문번호를 입력해주세요: ");
                    int orderNum = intInputCheck();
                    order.cancelOrder(orderNum);
                    order.clear();
                    continue;
                case 1, 2, 3: // 카테고리를 입력받은 경우
                    //해당 카테고리의 전체 상품 보여주기
                    Category selectedCategory = categoryMap.get(answer1);
                    printSelectedCategoryProductList(selectedCategory);
                    int answer2 = intInputCheck();
                    //해당 상품의 상세내용
                    if (answer2 == 0) {
                        System.out.println("뒤로가기를 선택하셨습니다.");
                        continue;
                    }
                    Product selectedProduct = selectedCategory.findProductByNum(answer2);
                    if (selectedProduct == null) {
                        System.out.println("올바른 번호를 입력해주세요. 3");
                        break;
                    } else {//위상품을 장바구니에 추가하시겠습니까? 1확인 2취소
                        printAddToCart(selectedProduct);
                        int answer3 = intInputCheck();

                        if (answer3 == 2) { // 2취소
                            System.out.println("취소를 선택하셨습니다.");
                            break;
                        } else if (answer3 == 1) { // 1확인
                            if(cart == null) {
                                cart = new ShoppingCart(customer);
                            }
                            cart.putProductToCart(selectedProduct, 1);
                            System.out.println(selectedProduct.getName() + "가 장바구니에 추가되었습니다.");
                            continue;

                        } else {
                            System.out.println("올바른 번호를 입력해주세요. 4");
                            break;
                        }
                    }

                default: // 카테고리 번호 잘못 입력
                    System.out.println("올바른 번호를 입력해주세요. 5");




            }
        }
    }
    public void add(List<Category>categoryList, List<Product> product) {
        categoryList.forEach(c -> categoryMap.put(c.getNum(), c));
        product.forEach(p -> productMap.put(p.getNum(), p));

    }

    public void printCategoryList(){

        Printer.printStart();
        Printer.printCategoryList(categoryMap.values());
        Printer.printEndMenu();
    }

    public void printSelectedCategoryProductList(Category selectedCategory){
        System.out.println("[ " + selectedCategory.getName() + " 카테고리 ]");
        Printer.printMenu(); //행이름
        Printer.printSelectedCategoryProductList(selectedCategory);//목록
        Printer.printBackMenu();//0. 뒤로가기
    }

    public void printAddToCart(Product selectedProduct){
        System.out.print("\"");
        System.out.print(selectedProduct);
        System.out.println("\"");
        Printer.printToCart(); // 위 상품을 장바구니에 추가하시겠습니까? 1확인 2취소
    }

    public void printCart(){
        Printer.printCartDetail(); // 장바구니 내역
        cart.printCart();
        Printer.printTotalPrice(); // 총 주문금액
        System.out.println(cart.getTotalPrice()+ "원");
    }


}
