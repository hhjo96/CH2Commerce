import java.util.ArrayList;
import java.util.List;

public class Category {

    private static int categoryNum = 0;
        private int num;
        private String name;
        private List<Product> products = new ArrayList<>();
        //카테고리의 미사용 처리 변수도 필요한가?

        Category(String name) {
            this.num = ++categoryNum;
            this.name = name;
        }

        public int getNum() {
            return this.num;
        }
        public String getName() {
            return this.name;
        }

        public String toString() { // 카테고리를 양식에 맞게 출력
            return String.format("%-2d. %s", this.getNum(), this.getName()) ;
        }
        public List<Product> getProducts() { // 카테고리의 상품 전체 리스트 리턴
            return this.products;
        }

        public void addProducts(List<Product> products) { // 카테고리에 상품리스트 추가
            this.products.addAll(products);
        }

        public Product findProductByNum(int num) {//카테고리를 받아서 상품리스트 리턴
            return this.products.stream().filter(p -> p.getNum() == num).findFirst().orElse(null);

        }
}
