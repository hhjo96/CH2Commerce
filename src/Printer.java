import java.util.List;

public class Printer {

    public static void printList(List<Product> productList) {
        System.out.printf("%-16s | %15s | %-13s | %-17s | %-5s%n","번호 상품명", "가격", "카테고리", "설명", "재고");
        for(Product p: productList){
            System.out.println(p);
        }
    }
    public static void printList(Product[] productList) {}
  //  public static void printList(Category[] categories) {}

    public static void printEnd(){
        System.out.println("커머스 플랫폼을 종료합니다.");
    }
    public static void printStart(){
        System.out.println("실시간 커머스 플랫폼 - 전자제품");
    }

    public static void printZero() {
        System.out.printf("%-19s | %13s |", "0.", "프로그램 종료");
        System.out.println();
    }
}
