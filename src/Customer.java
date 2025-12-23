public class Customer {
    private static int customerNum = 0;
    private int num;
        private String name;
        //private List<Order> orders
        private String email;
        private Level level = Level.Bronze;

        Customer(String name, String email) {
            this.num = ++customerNum;
            this.name = name;
            this.email = email;
        }

        public void setLevel(Level level) {
            this.level = level;
        }

        public int getNum() {
            return this.num;
        }
        public String getName() {
            return this.name;
        }
        public String getEmail() {
            return this.email;
        }
        public Level getLevel() {
            return this.level;
        }

}
