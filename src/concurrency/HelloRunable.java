package concurrency;

public class HelloRunable implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello from a thread");
    }

    public static void main(String[] args) {
        (new Thread(new HelloRunable())).start();
    }
    
}
