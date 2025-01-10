package conc;

public class ThreadExample9 {

    private static class MyRunnable implements Runnable {

        public static int counter = 0;

        @Override
        public void run() {
            counter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("counter: " + MyRunnable.counter);
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        thread.join();

        System.out.println("counter: " + MyRunnable.counter);
    }
}
