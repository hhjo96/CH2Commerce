public enum Level {
    Bronze(0), Silver(5), Gold(10), Platinum(15);
    private int level;
    Level(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }

    public static Level getLevelValue(int num) {
        return switch (num) {
            case 1 -> Bronze;
            case 2 -> Silver;
            case 3 -> Gold;
            case 4 -> Platinum;
            default -> Bronze;
        };
    }

}
