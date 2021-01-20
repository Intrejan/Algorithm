package myThread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " call()方法执行中...");
        return 1;
    }
}
