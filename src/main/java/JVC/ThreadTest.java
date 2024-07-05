package JVC;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static class RunnableTask implements Runnable{

        @Override
        public void run() {
            System.out.println("I am a child thread1");
        }
    }

    //创建任务类
    public static class CallerTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "hello";
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ThreadLocal<String>

        RunnableTask runnableTask = new RunnableTask();
        //创建线程
        MyThread myThread = new MyThread();
        //启动线程
        myThread.start();

        Thread myThread1 = new Thread(runnableTask,"Thread1");
        //启动线程
        myThread1.start();


        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        String s = futureTask.get();
        System.out.println(s);
    }
}
