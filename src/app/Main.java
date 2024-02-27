package app;

import app.threads.VegetableThread;
import app.threads.FruitThread;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1); // use for synchronized both threads
        ArrayList<Thread> listThreads = new ArrayList<>();

        FruitThread fruitThread = new FruitThread(latch, true);
        VegetableThread vegetableThread = new VegetableThread(latch, false);

        listThreads.add(fruitThread);
        listThreads.add(vegetableThread);
        Thread.UncaughtExceptionHandler handler = (t, e) -> {
            System.out.println("Uncaught exception in thread '" + t.getName() + "': " + e.getMessage());
            for (Thread thread : listThreads) {
                if (thread.isAlive())
                    thread.interrupt();
            }
            System.exit(1);
        };
        try {
            for (Thread thread : listThreads) {
                thread.setUncaughtExceptionHandler(handler);
                thread.start();
            }

            for (Thread thread : listThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.exit(1);
        }
    }
}