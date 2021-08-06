package concurrency.pc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Consumer implements Runnable {

    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String message = drop.take();
            !message.equals("DONE");
            message = drop.take()) {
            System.out.printf("MESSAGE RECEIVED: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException ignored) {}
        }
    }

    public static void main(String[] args) {
        Drop drop = new Drop();
        Consumer consumer = new Consumer(drop);
        Producer producer = new Producer(drop);
        new Thread(consumer).start();
        new Thread(producer).start();
    }
}
