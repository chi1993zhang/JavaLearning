package java0.Homework3.lockCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread03 extends Thread {
    private static volatile int count;
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    private static int sum() {
        System.out.println("start");
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();
        count = sum();
        condition.signal();
        lock.unlock();
    }

    public int getCount() throws InterruptedException {
        lock.lock();
        condition.await();
        int ret = count;
        lock.unlock();
        return ret;
    }
}