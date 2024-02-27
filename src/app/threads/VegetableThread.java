package app.threads;

import app.config.Config;
import app.utils.ThreadProcessing;

import java.util.concurrent.CountDownLatch;

public class VegetableThread extends Thread {
    private final CountDownLatch latchVegetable;
    private final boolean isAwait;
    private final String[] vegetableList = Config.vegetableList;

    public VegetableThread(CountDownLatch latch, boolean isAwait) {
        this.latchVegetable = latch;
        this.isAwait = isAwait;
        this.setName("=== VEGETABLE ===");
    }
    @Override
    public void run() {
        ThreadProcessing processingVegetableThread = new ThreadProcessing(this.latchVegetable, this.isAwait, vegetableList, this.getName());
        processingVegetableThread.processingThread();
    }
}
