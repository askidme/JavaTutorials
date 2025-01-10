package conc;

public class CheckResults {
    private static int counter;
    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 0; i < 500; i++) counter++;
        }).start();

        while (counter < 100){
            System.out.println("Not reached yet");
            ThreadUtil.sleep(1000);
        }

        System.out.println("Reached!");
    }
}
