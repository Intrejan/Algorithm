package feature;

public class RecordDemo {

    // Java 21及以后
    static void printSum(Object obj) {
        if (obj instanceof Point(int x, int y)) {
            System.out.println(x+y);
        }
    }

    static <S, T> void recordMyPair(MyPair<S, T> pair) {
        switch (pair) {
            case MyPair(String f, Integer s) -> System.out.println(f + ":" + s);
            case MyPair(Integer f, Integer s) -> System.out.println(f + ":" + s);
            default -> System.out.println("unknown");
        }
    }

    public static void main(String[] args) {
        printSum(new Point(1, 2));
        recordMyPair(new MyPair<>(2, 1));
    }
}

record Point(int x, int y) {

}

record MyPair<S, T> (S first, T second) {

}