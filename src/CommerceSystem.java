
import java.util.*;


public class CommerceSystem {

    Scanner sc = new Scanner(System.in);
    Map<Integer, Category> categoryMap = new HashMap<>();
    Map<Integer, Product> productMap = new HashMap<>();
    Customer customer;
    Map<Integer, ShoppingCart> cartMap = new HashMap<>();
    Map<Integer, Order> orderMap = new HashMap<>();

    public CommerceSystem(Customer customer) {
        this.customer = customer;

    }

    public Map<Integer, Product> getProductMap() {
        return this.productMap;
    }
    public Map<Integer, Category> getCategoryMap() {
        return this.categoryMap;
    }
    public Map<Integer, ShoppingCart> getCart() {
        return this.cartMap;
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
                System.out.println("올바른 숫자를 입력해주세요: ");
                sc.nextLine();
                continue;
            }
        }
    }

    public void start() {

        while (true) {
            //카테고리 목록 보여주기
            int answer1;

            printCategoryList();
            //장바구니에 상품이 들어있으면 주문관리 출력, 없으면 안출력, 예외처리
            if (cartMap.get(customer.getNum()) != null && !cartMap.get(customer.getNum()).isEmpty()) { // 카트내역이 있을 때
                Printer.printOrderManage(); //'주문 관리'
                Printer.printCartList(); // 4. 장바구니 확인
            }
            //주문내역이 있을 때
            boolean hasOrder = false;
            for(Order order : orderMap.values()) {
                if(order.getCustomer().getNum() == customer.getNum() && (order.getStatus().equals("done") || order.getStatus().equals("yet"))) { // 취소한 주문은 안보이게
                    hasOrder = true;
                    break;
                }
            }
            if(hasOrder){ // order != null && !order.isEmpty()
                //[ 주문 관리] 는 한번만 출력하기 위해 검사
                if(cartMap.get(customer.getNum()) == null || cartMap.get(customer.getNum()).isEmpty()) {
                    Printer.printOrderManage(); //'주문 관리'
                }
                Printer.printCancelOrder();// 5. 주문 취소
            }

            answer1 = intInputCheck();

            switch (answer1) { // 123: 카테고리, 0: 종료, 4: 장바구니확인, 5: 주문취소, 6: 관리자
                case 0://0 종료
                    Printer.printEnd();
                    return;
                case 4:
                    ShoppingCart myCart = cartMap.get(customer.getNum());
                    if (myCart == null || myCart.isEmpty()) { // 장바구니 상품 없을 때 -> 널이거나 empty
                        System.out.println("올바른 번호를 입력해주세요.");
                        break;
                    } else {// 장바구니 상품이 있을 때 장바구니 확인
                        printCart(customer.getNum());
                        Printer.printOrderOrBack(); // 주문할지 뒤로가기할지 물어보기
                        int answer2 = intInputCheck();

                        if (answer2 == 1) {//1 주문확정
                            //고객 등급 입력받아서
                            System.out.println("고객 등급을 입력해주세요.(1/2/3/4)");
                            Printer.printCustomerLevel();
                            int customerLevel = intInputCheck();

                            Level level = Level.getLevelValue(customerLevel);
                            customer.setLevel(level);

                            Order newOrder = new Order(customer, myCart);
                            //System.out.println("디버그: " + cart.getProducts().toString());
                            newOrder.toOrder();
                            orderMap.put(newOrder.getNum(), newOrder);
                            myCart.clear();
                            continue;
                        } else if(answer2 == 2) { // 2 메인으로 돌아가기
                            System.out.println("메인으로 돌아갑니다. ");
                            break;
                        } else if(answer2 == 3) { // 3 장바구니상품삭제
                            //여기에 삭제하는 로직
                            if(cartMap.get(customer.getNum()) != null) { // 카트에 상품이 있는경우
                                System.out.print("장바구니 내역에서 삭제할 상품의 이름을 입력해주세요: ");
                                String name = sc.nextLine();
                                cartMap.get(customer.getNum()).deleteProduct(name);

                            } else { // 카트에 상품이 없는경우
                                System.out.println("장바구니가 비어있습니다!");

                            }
                        } else {
                            System.out.println("올바른 번호를 입력해주세요.");
                            break;
                        }
                    }
                    break;
                case 5: // 주문 취소
                    System.out.print("취소할 주문의 주문번호를 입력해주세요: ");
                    int orderNum = intInputCheck();
                    Order cancelledOrder = orderMap.get(orderNum);

                    if(cancelledOrder == null) {
                        System.out.println("올바른 번호를 입력해주세요.");
                        break;
                    } else {
                        cancelledOrder.cancelOrder(orderNum, this.sc);
                        //orderMap.remove(orderNum);
                    }
                    continue;
                case 1, 2, 3: // 카테고리를 입력받은 경우
                    //해당 카테고리의 전체 상품 보여주기
                    Category selectedCategory = categoryMap.get(answer1);
                    //1전체상품 2백마넌이하 3백마넌초과 0종료
                    printSelectedCategoryProductList(selectedCategory);
                    continue;

                case 6: // 관리자모드
                    int i = 0;
                    while(true) {
                        if(i >= 3) break;
                        System.out.println("관리자 비밀번호를 입력해주세요: ");
                        String adminPassword = sc.nextLine();
                        if (adminPassword.equals("admin123")) {
                            Administrator admin = new Administrator(this);
                            Printer.printAdminMenu(); // 관리자 메뉴들 출력
                            int answer4 = intInputCheck();
                            switch (answer4) {
                                case 1: //상품 추가
                                    admin.adminAddProduct(this, sc);
                                    break;
                                case 2: //상품 수정
                                    admin.modifyProduct(this, sc);
                                    break;
                                case 3: //상품 삭제
                                    admin.deleteProduct(this, sc);
                                    break;
                                case 4: //전체 상품 현황
                                    Printer.printProductList(productMap.values());
                                    break;
                                case 0: //메인으로 돌아가기
                                    System.out.println("메인으로 돌아갑니다. ");
                                    break;
                                default:
                                    System.out.println("올바른 번호를 입력해주세요.");
                            }
                            break; // 관리자 작업 후 while 루프 종료

                        } else {
                            System.out.println("비밀번호가 틀렸습니다.");
                            i++;
                        }
                    }
                    break;
                default: // 카테고리 번호 잘못 입력
                    System.out.println("올바른 번호를 입력해주세요.  ");
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
        Printer.printAdmin();
    }

    public void printSelectedCategoryProductList(Category selectedCategory){
        System.out.println("[ " + selectedCategory.getName() + " 카테고리 ]");
        Printer.printFilteringMenu(); // 1 전체상품보기 2 100마넌이하 3 100마넌초과 4 뒤로가기
        int answer = intInputCheck();
        int answer2;
        Product selectedProduct;

        switch(answer){
            case 1: // 전체 상품 보기
                Printer.printMenu(); //행이름
                Printer.printSelectedCategoryProductList(selectedCategory);//목록
                Printer.printBackMenu();//0. 뒤로가기
                break;
            case 2: // 100마넌이하
                Printer.printMenu();
                Printer.printSelectedCategoryUnderPriceList(selectedCategory);
                Printer.printBackMenu();
                break;
            case 3: // 100마넌 초과
                Printer.printMenu();
                Printer.printSelectedCategoryOverPriceList(selectedCategory);
                Printer.printBackMenu();
                break;
            case 0:
                System.out.println("메인으로 돌아갑니다. ");
                return;
            default:
                System.out.println("올바른 번호를 입력해주세요.");
                return;
        }


        //해당 상품 클릭시 해당 상품의 상세정보 출력
        answer2 = intInputCheck();
        //해당 상품의 상세내용
        if (answer2 == 0) {
            System.out.println("뒤로가기를 선택하셨습니다.");
            return;
        }
        selectedProduct = selectedCategory.findProductByNum(answer2);

        //입력했던 조건에 맞지 않는 상품 입력시 안담기
        if(selectedProduct == null) {
            System.out.println("존재하지 않는 상품입니다.");
            return;
        }
        else if(answer == 2 && selectedProduct.getPrice() > 1000000) {
            System.out.println("목록에 없는 상품입니다.");
            return;
        } else if(answer == 3 && selectedProduct.getPrice() <= 1000000) {
            System.out.println("목록에 없는 상품입니다.");
            return;
        }

        putItemtoCart(this, selectedProduct); // 한개씩만 담으니까 개수는 필요없다

    }

    public void printAddToCart(Product selectedProduct){
        System.out.print("\"");
        System.out.print(selectedProduct);
        System.out.println("\"");
        Printer.printToCart(); // 위 상품을 장바구니에 추가하시겠습니까? 1확인 2취소 3검색
    }

    public void printCart(int num){
        Printer.printCartDetail(); // 장바구니 내역
        cartMap.get(num).printCart();
        Printer.printTotalPrice(); // 총 주문금액
        System.out.println(cartMap.get(num).getTotalPrice()+ "원");
    }

    public void putItemtoCart(CommerceSystem cs, Product p){

        ShoppingCart myCart;
        Product selectedProduct = p;
        if (selectedProduct == null) {
            System.out.println("올바른 번호를 입력해주세요.");
            return;

        } else {//위상품을 장바구니에 추가하시겠습니까? 1확인 2취소 3상품검색
            printAddToCart(selectedProduct);
            int answer3 = intInputCheck();

            if (answer3 == 2) { // 2취소
                System.out.println("취소를 선택하셨습니다.");
                return;
            } else if (answer3 == 1) { // 1확인
                checkCartAndPut(this.cartMap, selectedProduct);
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
                return;
            }
        }
    }

    public void checkCartAndPut(Map<Integer, ShoppingCart> cartMap, Product selectedProduct) {
        ShoppingCart myCart;
        if(cartMap.get(customer.getNum()) == null) { // 카트가 없는 경우
            myCart = new ShoppingCart(customer);
            cartMap.put(customer.getNum(), myCart);
        }

        if(selectedProduct.getStock() <= 0) {
            System.out.println("재고가 부족합니다. 장바구니에 담을 수 없습니다.");
            return;
        }
        if(selectedProduct.getStatus()) {
            myCart = cartMap.get(customer.getNum());
            myCart.putProductToCart(selectedProduct, 1);
            System.out.println(selectedProduct.getName() + "가 장바구니에 추가되었습니다.");
        } else { // selectedProduct.status == false 인 경우
            System.out.println("장바구니에 담을 수 없는 상품입니다.");
        }
    }
}
