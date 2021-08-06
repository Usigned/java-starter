package concurrency.pc;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        String[] infos = {
                "info 1",
                "info 2",
                "info 3"
        };

        Random random = new Random();

        for (String info : infos) {
            drop.put(info);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException ignored) {}
        }
        drop.put("DONE");
    }
}
