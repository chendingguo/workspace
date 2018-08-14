package multithread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用重入锁进行加锁是一种显式操作，通过何时加锁与释放锁使重入锁对逻辑控制的灵活性远远大于synchronized关键字。
 * 同时，需要注意，有加锁就必须有释放锁，而且加锁与释放锁的分数要相同，这里就引出了“重”字的概念，如上边代码演示，
 * 放开①、②处的注释，与原来效果一致。
 */
public class ReentrantLockTest implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();  // 看这里就可以

            //1
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock(); // 看这里就可以
                //2
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test = new ReentrantLockTest();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
        t1.join();
        t2.join(); // main线程会等待t1和t2都运行完再执行以后的流程
        System.err.println(i);
    }
}