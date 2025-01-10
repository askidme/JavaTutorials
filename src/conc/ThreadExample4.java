package conc;

public class ThreadExample4 {
    public static void main(String[] args) {
        Runnable runnable = () ->{
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " running");
        };

        Thread thread = new Thread(runnable, "The Thread 1");
        thread.start();
        Thread thread2 = new Thread(runnable, "The Thread 2");
        thread2.start();
    }
}
