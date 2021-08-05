package concurrency;

public class DeadLock {
    record Friend(String name) {

        public String getName() {
            return name;
        }

        public synchronized void bow(Friend bower) {
            // bower向this鞠躬
            System.out.printf("%s: %s"
                            + " has bowed to me!%n",
                    this.name, bower.getName());
            // this向bower回复
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.printf("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 当你向一个朋友鞠躬后，在朋友回复你之前，你要一直保持鞠躬
        final Friend alpha = new Friend("Alpha");
        final Friend beta = new Friend("Beta");
        new Thread(() -> alpha.bow(beta)).start();
        new Thread(() -> beta.bow(alpha)).start();
//        ((Runnable) () -> alpha.bow(beta)).run();
//        ((Runnable) () -> beta.bow(alpha)).run();

    }
}
