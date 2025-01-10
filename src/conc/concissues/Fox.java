package conc.concissues;

import conc.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Food{}
class Water{}

public class Fox {
    public void eatAndDring(Food food, Water water) {
        synchronized (food){
            System.out.println("Got food!");
            move();
            synchronized (water){
                System.out.println("Got water!");
            }
        }
    }

    public void drinkAndEat(Food food, Water water) {
        synchronized (water){
            System.out.println("Got water!");
            move();
            synchronized (food){
                System.out.println("Got food!");
            }
        }
    }

    public void move(){
        ThreadUtil.sleep(100);
    }

    public static void main(String[] args) {
        // Create participants and resources

        Fox foxy = new Fox();
        Fox tails = new Fox();
        Food food = new Food();
        Water water = new Water();

        // Process data
        try(ExecutorService service = Executors.newScheduledThreadPool(10)){
            service.submit(() -> foxy.eatAndDring(food, water));
            service.submit(() -> tails.drinkAndEat(food, water));
        }
    }
}
