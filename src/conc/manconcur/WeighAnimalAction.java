package conc.manconcur;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class WeighAnimalAction extends RecursiveAction {

    private int start;
    private int end;
    private Double[] weights;

    public WeighAnimalAction(int start, int end, Double[] weights) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected void compute() {
        if (end - start <= 3) {
            IntStream.range(start, end).forEach(i ->
            {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal weight: " + i);
            });
        } else {
            int middle = start + ((end - start) / 2);
            System.out.println("[start=" + start + ",end=" + end + ",middle=" + middle + "]");
            invokeAll(new WeighAnimalAction(start, middle, weights), new WeighAnimalAction(middle, end, weights));
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<?> task = new WeighAnimalAction(0, weights.length, weights);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        //print results
        System.out.println();
        System.out.println("Weights:");
        Arrays.asList(weights).forEach(i -> System.out.print(i.intValue() + " "));
    }
}
