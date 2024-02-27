package app.threads;

import app.config.Config;
import app.utils.ThreadProcessing;

import java.util.concurrent.CountDownLatch;

public class FruitThread extends Thread {
    private final CountDownLatch latchFruit;
    private final boolean isAwait;
    private final String[] fruitList = Config.fruitList;

    public FruitThread(CountDownLatch latch, boolean  isAwait) {
        this.latchFruit = latch;
        this.isAwait = isAwait;
        this.setName("=== FRUIT ===");
    }
    @Override
    public void run() {
        ThreadProcessing processingFruitThread = new ThreadProcessing(this.latchFruit, this.isAwait, fruitList, this.getName());
        processingFruitThread.processingThread();
    }
}
