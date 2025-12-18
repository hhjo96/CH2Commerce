import java.util.List;

public class Printer {

    public static void printProductList(List<Product> productList) {
        for(Product p: productList){
            System.out.println(p);
        }
    }
    public static void printCategoryList(List<Category> categoryList) {
        for(Category c: categoryList){
            System.out.println(c);
        }
    }

    public static void printEnd(){
        System.out.println("커머스 플랫폼을 종료합니다.");
    }
    public static void printStart(){
        System.out.println("실시간 커머스 플랫폼");
    }

    public static void printEndMenu() {
        System.out.printf("%-20s | %13s |", "0.", "프로그램 종료");
        System.out.println();

    }
    public static void printMenu() {
        System.out.printf("%-20s | %15s | %-13s | %-17s | %-5s%n","번호 상품명", "가격", "카테고리", "설명", "재고");
    }
}
