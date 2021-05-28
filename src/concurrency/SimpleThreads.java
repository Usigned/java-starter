package concurrency;

public class SimpleThreads {
    
    static void threadMessage(String message) {
        String name = Thread.currentThread().getName();
        System.out.format("%s: %s%n", name, message);
    }

    private static class MessageLoop implements Runnable {
        
        @Override
        public void run() {
            String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
            };
            try {
                for (String string : importantInfo) {
                    threadMessage(string);
                    Thread.sleep(4000);
                }
            } catch (InterruptedException e) {
                threadMessage("I am interrupted");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long patience = 10000;
        
        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("waiting for messageloop thread to finish");
        while(t.isAlive()) {
            threadMessage("Still alive");
            t.join(1000);
            // Thread.sleep(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("time's up");
                t.interrupt();
                t.join();
            }
        }
        threadMessage("finish");
    }
    
}