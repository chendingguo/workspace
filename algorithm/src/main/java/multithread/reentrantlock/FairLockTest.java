package multithread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁，就是按照时间先后顺序，使先等待的线程先得到锁，而且，公平锁不会产生饥饿锁，也就是只要排队等待，
 * 最终能等待到获取锁的机会。使用重入锁（默认是非公平锁）创建公平锁
 */
public class FairLockTest implements Runnable {
    public static ReentrantLock lock = new ReentrantLock(true);
    public static int i=1;

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();

                Thread.sleep(500);
                System.err.println(Thread.currentThread().getName() + "获取到了锁！"+i++);

            } catch(Exception e){

            } finally{
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FairLockTest test = new FairLockTest();
        Thread t1 = new Thread(test, "线程1");
        Thread t2 = new Thread(test, "线程2");
        t1.start();
        t2.start();
    }
}