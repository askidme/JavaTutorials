package conc;

public class MyRunnable implements Runnable {

    private int count = 0;
    Object date = null;

    public MyRunnable() {}
    public MyRunnable(Object date) {
        this.date = date;
    }
    @Override
    public void run() {

//        Object date = new Object();
        System.out.println(date);
        for (int i = 0; i < 1_000_000; i++) {
            synchronized (this){
                this.count++;
            }

        }

        System.out.println(Thread.currentThread().getName() + ": " + this.count);
    }
}
