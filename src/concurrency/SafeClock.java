package concurrency;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class SafeClock {
    static class Friend {
        /*
        name 名字
        lock 是否在鞠躬状态
         */
        private final String name;
        private final Lock lock = new ReentrantLock();

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean impendingBow(Friend bower) {
            /*
            为鞠躬做准备
            尝试获取自己的鞠躬锁，和对方的鞠躬锁
            如果某一个锁获取失败，则退回获取成功的锁
            当双方锁都已获取，则返回true
             */
            boolean myLock = false;
            boolean yourLock = false;
            try {
                myLock = lock.tryLock();
                yourLock = bower.lock.tryLock();
            } finally {
                if (! (myLock && yourLock)) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (yourLock) {
                        bower.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }

        public void bow(Friend bower) {
            /*
            向对方鞠躬
            首先尝试获取双方的锁
            - 获取成功则说明对方没有在尝试鞠躬，这时可以安全执行鞠躬操作, 执行完毕后放开双方的锁
            - 获取失败则说明对方也在尝试向己方鞠躬
             */
            if (impendingBow(bower)) {
                try {
                    System.out.printf("%s bows to %s%n",
                            this.name, bower.getName());
                    bower.bowBack(this);
                } finally {
                    lock.unlock();
                    bower.lock.unlock();
                }
            } else {
                System.out.printf("%s try to bow to %s, but failed%n",
                        this.name, bower.getName());
            }
        }

        public void bowBack(Friend bower) {
            System.out.printf("%s bows back to %s%n", this.name, bower.getName());
        }
    }


    static class BowLoop implements Runnable {
        private Friend bower;
        private Friend bowee;

        public BowLoop(Friend bower, Friend bowee) {
            this.bower = bower;
            this.bowee = bowee;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bowee.bow(bower);
            }
        }
    }

    public static void main(String[] args) {
        final Friend alpha = new Friend("Alpha");
        final Friend beta = new Friend("Beta");
        new Thread(new BowLoop(alpha, beta)).start();
        new Thread(new BowLoop(beta, alpha)).start();
    }

}
