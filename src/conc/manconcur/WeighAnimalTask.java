package conc.manconcur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class WeighAnimalTask extends RecursiveTask<Double> {
    private int start;
    private int end;
    private List<Double> weights;

    public WeighAnimalTask(List<Double> weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected Double compute() {
        if (end - start <= 3) {
            System.out.println("[start2 = " + start + ", end2 = " + end + "]");
            weights.addAll(IntStream.range(start, end).boxed()
                    .peek(i -> System.out.println("Animal weighed: " + i))
                    .map(i -> (double) new Random().nextInt(100))
                    .collect(Collectors.toList()));
            return weights.stream().reduce((a, b) -> a + b).get();
        } else {
            int middle = start + ((end - start) / 2);
            System.out.println("[start = " + start + ", end = " + end + "]");
            RecursiveTask<Double> otherTask = new WeighAnimalTask(weights, start, middle);
            otherTask.fork();
            return new WeighAnimalTask(weights, middle, end).compute() + otherTask.join();
        }
    }

    public static void main(String[] args) {
        List<Double> weights = new ArrayList<>();
        ForkJoinTask<Double> task = new WeighAnimalTask(weights, 0, 10);
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);
        System.out.println("sum = " + sum);
    }
}
