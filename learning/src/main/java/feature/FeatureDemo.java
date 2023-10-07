package feature;

public class FeatureDemo {

    // Java 21及以后
    static void printSum(Object obj) {
        if (obj instanceof Point(int x, int y)) {
            System.out.println(x+y);
        }
    }
}

