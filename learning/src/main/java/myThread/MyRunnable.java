package myThread;

public class MyRunnable implements Runnable{
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run()方法正在执行...");
    }
}
