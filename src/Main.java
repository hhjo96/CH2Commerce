import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Product(String name, int price, String category, String description, int status, int stock) {
        Product GalaxyZflip7 = new Product("Galaxy Zflip 7", 1485000, "Electronics", "갤럭시 Z플립 7", 1, 10);
        Product iPadPro13 = new Product("iPad Pro 13", 2099000, "Electronics", "아이패드 프로 13세대", 1, 10);
        Product iPhone17 = new Product("iPhone 17", 1290000, "Electronics", "아이폰 17", 1, 10);
        Product GalaxyBuds = new Product("Galaxy Buds 3", 169000, "Electronics", "갤럭시 버즈 3", 1, 10);
        Product MacBookAir13 = new Product("MacBook Air 13", 1590000, "Electronics", "맥북 에어 13인치", 1, 10);

        List<Product> productList = new ArrayList<>();

        productList.add(GalaxyZflip7);
        productList.add(iPadPro13);
        productList.add(iPhone17);
        productList.add(GalaxyBuds);
        productList.add(MacBookAir13);

        CommerceSystem cs = new CommerceSystem(productList);

        cs.start();
    }
}
