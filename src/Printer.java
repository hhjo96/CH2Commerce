import java.util.Collection;
import java.util.List;

public class Printer {

    public static void printProductList(Collection<Product> productList) {// 전체 상품 출력

        boolean activeProduct = productList.stream().anyMatch(Product::getStatus);// 액티브 product 가 있다면 true
        if(productList.isEmpty() || !activeProduct){
            System.out.println("상품 내역이 없습니다.");
            return;
        }
        //기존 출력 함수
//        for(Product p: productList){
//            if(p.getStatus()) {
//                System.out.println(p);
//            }
//        }
        productList.stream().filter(Product::getStatus).forEach(System.out::println);
    }

    public static void printSelectedCategoryProductList(Category category) {//카테고리에 맞는 상품만 출력
        List<Product> products = category.getProducts();
        printProductList(products);
    }

    public static void printCategoryList(Collection<Category> categoryList) {//전체 카테고리 출력
        for(Category c: categoryList){
            System.out.println(c);
        }
    }

    public static void printEnd(){ // 종료합니다 출력
        System.out.println("커머스 플랫폼을 종료합니다.");
    }
    public static void printStart(){ // 시작 출력
        System.out.println("========== 실시간 커머스 플랫폼 ==========");
    }

    public static void printEndMenu() { // 0.종료를 양식에 맞게 출력
        System.out.printf("%-20s | %13s |", "0.", "프로그램 종료");
        System.out.println();

    }
    public static void printMenu() { // 번호 상품명(행이름)
        System.out.printf("%-20s | %15s | %-13s | %-17s | %-5s%n","번호 상품명", "가격", "카테고리", "설명", "재고");
    }
    public static void printBackMenu() { // 뒤로가기를 양식에 맞게 출력
        System.out.printf("%-20s | %13s |", "0.", "뒤로가기");
        System.out.println();
    }

    public static void printToCart() {
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.printf("%-20s | %13s |","1. 확인", "2. 취소");
        System.out.println();
    }

    public static void printOrderManage(){
        System.out.println("[ 주문 관리 ]");
    }

    public static void printCartList() {
        System.out.printf("%-20s | %13s |\n", "4. 장바구니 확인", "장바구니를 확인 후 주문합니다.");
    }

    public static void printCancelOrder() {
        System.out.printf("%-20s | %13s |\n", "5. 주문 취소", "진행 중인 주문을 취소합니다.");
    }

    public static void printCartDetail() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println("[ 장바구니 내역] ");
    }
    public static void printTotalPrice(){
        System.out.println("[ 총 주문 금액 ]");
    }

    public static void printOrderOrBack() {
        System.out.printf("%-20s | %13s |","1. 주문 확정", "2. 메인으로 돌아가기");
        System.out.println();
    }

     static void printAdmin() {
        System.out.printf("%-20s |\n", "6. 관리자 모드");
    }
     static void printAdminMenu(){
        System.out.println("[ 관리자 모드 ]");
        System.out.printf("%-20s |\n", "1. 상품 추가");
        System.out.printf("%-20s |\n", "2. 상품 수정");
        System.out.printf("%-20s |\n", "3. 상품 삭제");
        System.out.printf("%-20s |\n", "4. 전체 상품 현황");
        System.out.printf("%-20s |\n", "0. 메인으로 돌아가기");
    }

     static void printAdminAddProduct(Collection<Category> categoryList){
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        for(Category c: categoryList){
            System.out.println(c.getNum() + ". "+ c.getName());
        }
    }

    public static void printCustomerLevel() {
        System.out.printf("%-20s : %-20s\n", "1. BRONZE", "0% 할인");
        System.out.printf("%-20s : %-20s\n", "2. SILVER", "5% 할인");
        System.out.printf("%-20s : %-20s\n", "3. GOLD", "10% 할인");
        System.out.printf("%-20s : %-20s\n", "4. PLATINUM", "15% 할인");

    }

}
