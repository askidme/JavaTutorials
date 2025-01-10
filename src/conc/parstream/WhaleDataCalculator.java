package conc.parstream;

import conc.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WhaleDataCalculator {

    public int processRecord(int input){
        ThreadUtil.sleep(10);
        return input + 1;
    }

    public void processAllData(List<Integer> data){
        data.stream().map(a -> processRecord(a)).reduce((a, b) -> a + b).get();
    }
    public void processAllDataConcurrently(List<Integer> data){
        data.parallelStream().map(a -> processRecord(a)).reduce((a, b) -> a + b).get();
    }

    public static void main(String[] args) {
        WhaleDataCalculator calculator = new WhaleDataCalculator();

        //Define the data
        List<Integer> data = new ArrayList<>();
        IntStream.range(0, 4000).forEach(i -> data.add(i));

        //Process the data
        long start = System.currentTimeMillis();
        calculator.processAllData(data);
        double time = (System.currentTimeMillis() - start) / 1000.00;
        System.out.println("\nSerial Tasks completed in " + time + " seconds");

        //Process the data concurrently
        start = System.currentTimeMillis();
        calculator.processAllDataConcurrently(data);
        time = (System.currentTimeMillis() - start) / 1000.00;
        System.out.println("\nParallel Tasks completed in " + time + " seconds");
    }
}
