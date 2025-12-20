
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
    }

    public int intInputCheck(){ // 숫자인지 체크
        int answer;
        while(true) {
            try{
                answer = sc.nextInt();
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
            //이 위치에 장바구니에 상품이 들어있으면 주문관리 출력, 없으면 안출력, 예외처리까지 해야 함.
            if(cart != null) {
                Printer.printOrderList();
            }
            answer1 = intInputCheck();

            if (answer1 == 0) {//0 종료
                Printer.printEnd();
                break;
            } else if(answer1 == 4) { // 4 장바구니 확인
                if(cart == null) {
                    System.out.println("주문 내역이 없습니다.");
                } else {
                    printCart();
                    Printer.printOrderOrBack();
                    int answer2 = intInputCheck();
                    if(answer2 == 1) {
                        order = new Order(customer, cart);
                        order.toOrder(cart.getProducts());
                        System.out.println("주문이 완료되었습니다!"+ "총 금액: "+ cart.getTotalPrice()+"원");
                        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
                            Product product = entry.getKey();
                            int count = entry.getValue();
                            System.out.println(product+" 재고가 "+entry.getValue()+"개로 업데이트되었습니다.");
                        }

                        cart = null;
                        order = null;
                    }

                }



            } else if(answer1 == 5) { //5 주문 취소

            }

            Category selectedCategory = categoryMap.get(answer1);
            if(selectedCategory == null) {
                System.out.println("올바른 번호를 입력해주세요.");
            } else {//해당 카테고리의 전체 상품 보여주기
                while(true){
                    printSelectedCategoryProductList(selectedCategory);
                    int answer2 = intInputCheck();
                    //해당 상품의 상세내용
                    if (answer2 == 0) {
                        System.out.println("뒤로가기를 선택하셨습니다.");
                        continue;
                    }
                    Product selectedProduct = selectedCategory.findProductByNum(answer2);
                    if(selectedProduct == null) {
                        System.out.println("올바른 번호를 입력해주세요.");
                    } else {
                        printAddToCart(selectedProduct);
                        int answer3 = intInputCheck();
                        if(answer3 == 2) {
                            System.out.println("취소를 선택하셨습니다.");
                            continue;
                        } else if(answer3 == 1) {
                        // !!!!!!!!!!!!!!!!!!!!!장바구니 추가 로직
                            cart = new ShoppingCart(customer);
                            cart.putProductToCart(selectedProduct, 1);
                            System.out.println(selectedProduct.getName()+ "가 장바구니에 추가되었습니다.");


                        } else {
                            System.out.println("올바른 번호를 입력해주세요.");
                            continue;
                        }
                }


                }
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
        Printer.printCartDetail();
        cart.printCart();
        Printer.printTotalPrice();
        System.out.println(cart.getTotalPrice()+ "원");
    }


}
