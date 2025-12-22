import java.util.Scanner;

public class Administrator {

    CommerceSystem cs;

    Administrator(CommerceSystem cs) {
        this.cs = cs;
    }

    public Product findByProductName(String productName) {
        for (Product product : cs.productMap.values()) {
            if (product.getName().equals(productName) && product.getStatus()) {
                return product;
            }
        }
        return null; // 못 찾은 경우
    }

    void addProduct(Category c, Product p) { // 카테고리는 이미 있고 상품만 추가
        cs.productMap.put(p.getNum(), p);
        c.getProducts().add(p);

    }

    void adminAddProduct(CommerceSystem cs, Scanner sc) {
        Printer.printAdminAddProduct(this.cs.getCategoryMap().values()); // 어느 카테고리에 상품추가?
        int answerC = cs.intInputCheck();

        Category selectedCategory = this.cs.getCategoryMap().get(answerC); // 해당 카테고리에 상품 추가

        //상품 입력받기
        System.out.print("상품명을 입력해주세요: ");
        String productName = sc.nextLine();
        if (findByProductName(productName) != null) {
            System.out.println("이미 존재하는 상품입니다.");
            return;
        }
        System.out.print("가격을 입력해주세요: ");
        int price = cs.intInputCheck();
        System.out.print("상품 설명을 입력해주세요: ");
        String description = sc.nextLine();
        System.out.print("재고수량을 입력해주세요: ");
        int stock = cs.intInputCheck();

        // 중복 품목이 없다면 추가
        Product product = new Product(productName, price, selectedCategory.getName(), description, stock);
        System.out.println(product);
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.printf("%-20s | %-20s |\n", "1. 상품 추가", "2. 취소");
        int answer = cs.intInputCheck();

        if (answer == 1) {
            this.addProduct(selectedCategory, product);
            System.out.println("상품이 성공적으로 추가되었습니다!");
        }

    }

    void modifyProduct(CommerceSystem cs, Scanner sc) {
        System.out.println("수정할 상품명을 입력해주세요");
        String productName = sc.nextLine().trim();
        Product product = findByProductName(productName);

        if (product == null) { // 상품명 인식이 안 될 경우 리턴
            System.out.println("올바른 상품명을 입력해주세요.");
            return;
        }

        System.out.println("현재 상품 정보: " + product);
        System.out.println("수정할 항목을 선택해주세요: ");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고수량");

        int answer = cs.intInputCheck();

        switch (answer) {
            case 1:
                System.out.println("현재 가격: " + product.getPrice());
                System.out.print("새로운 가격을 입력해주세요: ");
                int newPrice = cs.intInputCheck();
                product.setPrice(newPrice);
                System.out.println("변경되었습니다. " + product.getPrice());
                break;
            case 2:
                System.out.println("현재 설명: " + product.getDescription());
                System.out.print("새로운 설명을 입력해주세요: ");
                String newDescription = sc.nextLine();
                product.setDescription(newDescription);
                System.out.println("변경되었습니다. " + product.getDescription());
                break;
            case 3:
                System.out.println("현재 재고수량: " + product.getStock());
                System.out.print("새로운 재고수량을 입력해주세요: ");
                int newStock = cs.intInputCheck();
                product.setStock(newStock);
                System.out.println("변경되었습니다. " + product.getStock());
                break;
            default:
                System.out.println("올바른 숫자를 입력해주세요.");
        }
    }

    void deleteProduct(CommerceSystem cs, Scanner sc) {
        System.out.print("삭제할 상품명을 입력하세요: ");
        String name = sc.nextLine();
        System.out.print("삭제할 상품명이 "+ name+ "이 맞나요?(y or n): ");
        String answer = sc.nextLine();
        Product product = findByProductName(name);

        if(answer.equalsIgnoreCase("y") && product != null) {
            product.setStatus(false); // 논리 삭제만 수행
            System.out.println("삭제를 완료하였습니다.");

        } else if(answer.equalsIgnoreCase("n")){
            System.out.println("삭제를 취소하셨습니다.");
        } else if(product == null) {
            System.out.println("해당하는 상품이 없습니다.");
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }
}
