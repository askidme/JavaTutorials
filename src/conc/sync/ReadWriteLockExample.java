package conc.sync;

import java.io.Writer;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Data{
    private int value;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void write(int value){
        lock.writeLock().lock();
        try{
            this.value = value;
            System.out.println("Written: " + value);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public int read(){
        lock.readLock().lock();
        try {
            System.out.println("Read: " + value);
            return value;
        }finally {
            lock.readLock().unlock();
        }
    }
}

public class ReadWriteLockExample {
    public static void main(String[] args) {
        Data data = new Data();
        Thread writer = new Thread(() -> data.write(42));
        Thread reader = new Thread(() -> data.read());

        writer.start();
        reader.start();
    }
}
