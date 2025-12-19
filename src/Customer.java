public class Customer {
    private static int customerNum = 0;
    private int num;
        private String name;
        //private List<Order> orders
        private String email;
        private String rating = "일반";

        Customer(String name, String email) {
            this.num = ++customerNum;
            this.name = name;
            this.email = email;
        }


        public String getName() {
            return this.name;
        }
        public String getEmail() {
            return this.email;
        }
        public String getRating() {
            return this.rating;
        }

//        public List<Order> getOrderList() {
//
//        }
}
