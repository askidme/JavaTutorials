package conc;

public class SeparateObjects {
    public static void main(String[] args) {
        Object object = new Object();
        Runnable myRunnable = new MyRunnable(object);
        Runnable myRunnable2 = new MyRunnable(object);

        Thread t1 = new Thread(myRunnable, "The Thread 1");
        Thread t2 = new Thread(myRunnable2, "The Thread 2");

        t1.start();
        t2.start();
    }
}
