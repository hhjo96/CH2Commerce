
import java.util.*;


public class CommerceSystem {

    Scanner sc = new Scanner(System.in);
    Map<Integer, Category> categoryMap = new HashMap<>();
    Map<Integer, Product> productMap = new HashMap<>();
    Map<Product, Category> productCategoryMap = new HashMap<>();

    public CommerceSystem() {
    }

    public int intInputCheck(){ // 숫자인지 체크
        int answer;
        while(true) {
            try{
                answer = sc.nextInt();
                return answer;
            } catch (InputMismatchException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
                sc.nextLine();
            }
        }


    }

    public void start() {
        while (true) {
            //카테고리 목록 보여주기
            int answer1;

            Printer.printStart();
            Printer.printCategoryList(categoryMap.values());
            Printer.printEndMenu();
            answer1 = intInputCheck();
            if (answer1 == 0) {
                Printer.printEnd();
                break;
            }
            Category selectedCategory = categoryMap.get(answer1);
            if(selectedCategory == null) {
                System.out.println("올바른 번호를 입력해주세요.");
            } else {//해당 카테고리의 전체 상품 보여주기
                System.out.println("[ " + selectedCategory.getName() + " 카테고리 ]");
                Printer.printMenu(); //행이름
                Printer.printSelectedCategoryProductList(selectedCategory);//목록
                Printer.printBackMenu();//0. 뒤로가기
                int answer2 = intInputCheck();
                //해당 상품의 상세내용
                if (answer2 == 0) {
                    System.out.println("뒤로가기를 선택하셨습니다.");
                    continue;
                }
                Product selectedProduct = selectedCategory.findProductByNum(answer2);
                if(selectedProduct == null) {
                    System.out.println("올바른 번호를 입력해주세요.");
                } else {
                    System.out.println(selectedProduct);
                    System.out.println();

                }
            }
        }
    }
// 이 아래 두 함수를 합치고싶음!! 메인함수가 너무 길어져서
    public void add(List<Category>categoryList, List<Product> product) {
        for(int i = 0; i < product.size(); i++) {
            productMap.put(product.get(i).getNum(), product.get(i));
        }

        for(int i = 0; i < categoryList.size(); i++) {
            categoryMap.put(categoryList.get(i).getNum(), categoryList.get(i));
        }


    }

}
