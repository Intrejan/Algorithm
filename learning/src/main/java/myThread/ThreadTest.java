package myThread;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 四种启动线程方式
 */
public class ThreadTest {
    public static void main(String[] args){
        //继承Thread类
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println(Thread.currentThread().getName() + " main()方法1执行结束");

        //实现Runnable接口
        MyRunnable myRunnable = new MyRunnable();
        Thread thread2 = new Thread(myRunnable);
        thread2.start();
        System.out.println(Thread.currentThread().getName() + " main()方法2执行结束");

        //实现Callable接口
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCallable());
        Thread thread3 = new Thread(futureTask);
        thread3.start();
        try {
            Thread.sleep(1000);
            System.out.println("返回结果 " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " main()方法3执行完成");

        //使用 Executors 工具类创建线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        MyRunnable runnableTest = new MyRunnable();
        for (int i = 0; i < 5; i++) {
            executorService.execute(runnableTest);
        }
        System.out.println("线程任务开始执行");
        executorService.shutdown();

    }
}
