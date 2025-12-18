
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CommerceSystem {

    Scanner sc = new Scanner(System.in);
        private List<Product> productList= new ArrayList<Product>();
  //  private List<Category> categories
     //   private List<Customer> customers
    //    private List<Order> orders
    //    private List<ShoppingCart> shoppingCarts

    public CommerceSystem(List<Product> productList) {
        this.productList = productList;
    }

        public void start() {
            while(true){
                Printer.printStart();
                Printer.printList(productList);
                Printer.printZero();
                if(sc.nextInt() == 0) break;
            }
            Printer.printEnd();
        }


}
