package conc;

public class SharedObjects {
    public static void main(String[] args) {
        Object object = new Object();
        Runnable runnable = new MyRunnable(object);
        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");
        thread1.start();
        thread2.start();
    }
}
