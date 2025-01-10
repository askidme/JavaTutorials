package conc;

public class ThreadExample3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() ->{
            System.out.println("Runnable running");
            System.out.println("Runnable finished");
        });

        t1.start();
    }
}
