package conc;

public class ThreadExample6 {
    public static class StoppableRunnable implements Runnable {

        public boolean stopRequested = false;

        public synchronized void requestStop() {
            this.stopRequested = true;
        }

        public synchronized boolean isStopRequested() {
            return this.stopRequested;
        }

        private void sleep(long millis){
            try {
                Thread.sleep(millis);
            }catch (InterruptedException exception){
                exception.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("StoppableRunnable Running");

            while (!isStopRequested()){
                sleep(1000);
                System.out.println("...");
            }
            System.out.println("StoppableRunnable Stopped");
        }
    }
    public static void main(String[] args) {
        StoppableRunnable runnable = new StoppableRunnable();
        Thread thread = new Thread(runnable, "The Thread");
        thread.start();

        try {
            Thread.sleep(5000);
        }catch (InterruptedException exception){

        }

        System.out.println("Requesting stop");
        runnable.requestStop();
//        thread.stop();
        System.out.println("stop requested");
    }
}
