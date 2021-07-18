package java0.Homework3.futureTask;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class MyThread01 implements Callable {
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    @Override
    public Object call() throws Exception {
        sleep(3000);
        return sum();
    }
}
