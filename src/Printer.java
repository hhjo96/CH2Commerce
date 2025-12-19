import java.util.Collection;
import java.util.List;

public class Printer {

    public static void printProductList(Collection<Product> productList) {// 전체 상품 출력
        for(Product p: productList){
            System.out.println(p);
        }
    }

    public static void printSelectedCategoryProductList(Category category) {//카테고리에 맞는 상품만 출력
        List<Product> products = category.getProducts();
        for(Product p: products) {
            System.out.println(p);
        }
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
        System.out.println("실시간 커머스 플랫폼");
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
}
