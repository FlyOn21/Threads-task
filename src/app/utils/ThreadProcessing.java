package app.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.logging.Level;
import app.config.Config;

public class ThreadProcessing {
    private  final String[] array;
    private final boolean isAwait;
    private final CountDownLatch latch;
    private final String title;

    private final Logger logger;


    public ThreadProcessing(CountDownLatch latch, boolean  isAwait, String[] array, String title) {
        this.latch = latch;
        this.isAwait = isAwait;
        this.array = array;
        this.title = title;
        this.logger = Logger.getLogger(ThreadProcessing.class.getName());
        if (Config.isDebug) {
            logger.setLevel(Level.ALL);
        } else {
            logger.setLevel(Level.SEVERE);
        }
    }
    public void processingThread () {
        AtomicInteger atomicInteger = new AtomicInteger();
        try {
            if (isAwait) {
                latch.await();
            }
            logger.info("Thread " + Thread.currentThread().getName() + " started");
            System.out.println(title);
            for (String unit : array) {
                int number = atomicInteger.incrementAndGet();
                System.out.println(number + "  " + unit);
                Thread.sleep(1000);
            }
            if (!isAwait) {
                latch.countDown();
            }
        } catch (InterruptedException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
