public class Product {

    static int productNum = 0;
    private int num;
    private String name = ""; // 상품명
    private int price = -1;
    private String category = "";
    private String description = "";
    private boolean status = true; //
    private int stock = 0;

    Product(String name, int price, String category, String description, int stock) {
        this.num = ++productNum;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
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

    public boolean getStatus() {
        return this.status;
    }

    public int getStock() {
        return this.stock;
    }

    public void plusStock(int count) {
        this.stock += count;
    }

    public void minusStock(int count) {
        if(this.stock < count) {
            System.err.println("실행할 수 없습니다. 재고가 부족합니다. 현재 재고: "+ this.getStock());
        }
        this.stock -= count;
    }

    //String name, int price, String category, String description, int status, int stock

    @Override
    public String toString() {//상품을 양식에 맞게 출력
        return String.format("%-2d. %-18s | %,15d원 | %-15s | %-15s | %-5d", this.getNum(), this.getName(), this.getPrice(), this.getCategory(), this.getDescription(), this.getStock());
    }

}
