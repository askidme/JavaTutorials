package conc;

public class ThreadExample5 {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " running");
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(threadName + " finished");
        };
        Thread thread = new Thread(runnable, "The Thread");
        thread.start();
    }

}
