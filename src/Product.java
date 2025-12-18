public class Product {

    static int productNum = 0;
    private int num;
    private String name = ""; // 상품명
    private int price = -1;
    private String category = "";
    private String description = "";
    private int status = 0; // 0 미사용/ 1 사용
    private int stock = 0;

    Product(String name, int price, String category, String description, int status, int stock) {
        this.num = productNum++;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.status = status;
        this.stock = stock;
    }
    public int getNum() {
        return num;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStatus() {
        return this.status;
    }

    public int getStock() {
        return this.stock;
    }

    public void plusStock(int count) {
        this.stock += count;
    }

    public void minusStock(int count) {
        this.stock -= count;
    }

    //String name, int price, String category, String description, int status, int stock

    @Override
    public String toString() {
        return String.format("%-2d. %-15s | %,15d원 | %-15s | %-15s | %-5d", this.getNum(), this.getName(), this.getPrice(), this.getCategory(), this.getDescription(), this.getStock());
    }
}
