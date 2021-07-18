package java0.Homework3.waitNotify;

import static java.lang.Thread.sleep;

public class MyThread02 extends Thread {
    private static volatile int count;

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

        synchronized(this) {
            count = sum();
            this.notify();
        }
    }

    public int getCount() throws InterruptedException {
        synchronized(this) {
            this.wait();
            return count;
        }
    }
}