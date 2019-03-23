package multithread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 对于synchronized块来说，要么获取到锁执行，要么持续等待。而重入锁的中断响应功能就合理地避免了这样的情况。
 * 比如，一个正在等待获取锁的线程被“告知”无须继续等待下去，就可以停止工作了。
 * 此代码，来演示使用重入锁如何解决死锁
 */
public class InterruptDeadlock implements Runnable{
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public InterruptDeadlock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();  // 以可以响应中断的方式加锁
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();  // 以可以响应中断的方式加锁
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) lock1.unlock();  // 注意判断方式
            if (lock2.isHeldByCurrentThread()) lock2.unlock();
            System.err.println(Thread.currentThread().getId() + "退出！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptDeadlock deadLock1 = new InterruptDeadlock(1);
        InterruptDeadlock deadLock2 = new InterruptDeadlock(2);
        Thread t1 = new Thread(deadLock1);
        Thread t2 = new Thread(deadLock2);
        t1.start();t2.start();
        Thread.sleep(2000);
        t2.interrupt(); // ③
    }
}