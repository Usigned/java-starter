package concurrency.stdPC;

import java.util.concurrent.*;

public class Producer implements Runnable {
    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String[] infos = {
                "info 1",
                "info 2",
                "info 3"
        };
        try {
            for (String info : infos) {
                queue.put(info);
            }
            queue.put("DONE");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Consumer(queue));
        executor.execute(new Producer(queue));
        executor.shutdown();
        if (!executor.awaitTermination(60, TimeUnit.SECONDS))
            executor.shutdownNow();
    }
}
