import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Customer c = new Customer("커머스", "email@email.com");

        Category electronics = new Category("Electronics");
        Category clothes = new Category("Clothes");
        Category food = new Category("Food");

        //Product (String name, int price, String category, String description, int status, int stock)
        Product GalaxyZflip7 = new Product("Galaxy Zflip 7", 1485000, "Electronics", "갤럭시 Z플립 7", 10);
        Product iPadPro13 = new Product("iPad Pro 13", 2099000, "Electronics", "아이패드 프로 13세대", 10);
        Product iPhone17 = new Product("iPhone 17", 1290000, "Electronics", "아이폰 17", 10);
        Product GalaxyBuds = new Product("Galaxy Buds 3", 169000, "Electronics", "갤럭시 버즈 3", 10);
        Product MacBookAir13 = new Product("MacBook Air 13", 1590000, "Electronics", "맥북 에어 13인치", 10);
        Product pajamaWhite = new Product("수면 파자마(WHITE)", 49000, "Clothes", "수면 잠옷(WHITE)", 10);
        Product pajamaBlack = new Product("수면 파자마(BLACK)", 49000, "Clothes", "수면 잠옷(BLACK)", 10);
        Product shortSleeveWhite = new Product("반팔 티셔츠(WHITE)", 25900, "Clothes", "반팔 티셔츠(WHITE)", 10);
        Product shortSleeveCharcoal = new Product("반팔 티셔츠(CHARCOAL)", 25900, "Clothes", "반팔 티셔츠(CHARCOAL)", 10);
        Product waterbottle24 = new Product("삼다수 2L * 24개", 17190, "Food", "삼다수 2L 24개 세트", 10);
        Product waterbottle6 = new Product("삼다수 2L * 6개", 10560, "Food", "삼다수 2L 6개 세트", 10);
        Product mandarin = new Product("제주 감귤 5kg/박스", 23100, "Food", "제주 감귤 5kg", 10);

        List<Category> categoryList = new ArrayList<>(List.of(electronics, clothes, food));

        List<Product> productList = new ArrayList<>(List.of(GalaxyZflip7, iPadPro13, iPhone17, GalaxyBuds, MacBookAir13,
                pajamaWhite, pajamaBlack, shortSleeveWhite, shortSleeveCharcoal,
                waterbottle24, waterbottle6, mandarin));

        List<Product> electroProductList = new ArrayList<>(List.of(GalaxyZflip7, iPadPro13, iPhone17, GalaxyBuds, MacBookAir13));
        List<Product> clothesProductList = new ArrayList<>(List.of(pajamaWhite, pajamaBlack, shortSleeveWhite, shortSleeveCharcoal));
        List<Product> foodProductList = new ArrayList<>(List.of(waterbottle24, waterbottle6, mandarin));

        electronics.addProducts(electroProductList);
        clothes.addProducts(clothesProductList);
        food.addProducts(foodProductList);


        CommerceSystem cs = new CommerceSystem(c);

        cs.add(categoryList, productList);


        cs.start();
    }
}
