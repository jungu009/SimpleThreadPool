package cn.fc.simplethreadpool.schedule;

import java.util.concurrent.ScheduledFuture;

public abstract class EndRunnable implements Runnable {
    private ScheduledFuture future;

    public ScheduledFuture getFuture() {
        return future;
    }

    public void setFuture(ScheduledFuture future) {
        this.future = future;
    }

    @Override
    public void run() {
        future.cancel(true);
        runTask();
    }

    protected abstract void runTask();


}
