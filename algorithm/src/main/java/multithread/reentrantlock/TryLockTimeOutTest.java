package multithread.reentrantlock;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * t1先获取到锁，并休眠2秒，这时t2开始等待，等待1秒后依然没有获取到锁，就不再继续等待，符合预期结果。
 */
public class TryLockTimeOutTest implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) { // 等待1秒
                Thread.sleep(2000);  //休眠2秒
                System.out.println(Thread.currentThread().getName()+"获取锁成功");
            } else {
                System.err.println(Thread.currentThread().getName() + "获取锁失败！");
            }
        } catch (Exception e) {
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TryLockTimeOutTest test = new TryLockTimeOutTest();
        Thread t1 = new Thread(test);
        t1.setName("线程1");
        Thread t2 = new Thread(test);
        t1.setName("线程2");
        t2.start();
        t1.start();
    }
}