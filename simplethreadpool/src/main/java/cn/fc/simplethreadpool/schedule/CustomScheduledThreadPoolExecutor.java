package cn.fc.simplethreadpool.schedule;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class CustomScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    public CustomScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    public CustomScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public CustomScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public CustomScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    /**
     * 任务(runnable)按照指定的频率(period)运行，知道到达定时时间(dieout)
     * @param runnable 任务
     * @param initialDelay 第一次执行的时间，什么时候开始执行  ms
     * @param period 执行频率  ms
     * @param dieout 定时时间 s
     */
    public void schedule(Runnable runnable, int initialDelay, int period, int dieout){
        ScheduledFuture future = this.scheduleAtFixedRate(runnable,
                        initialDelay,
                        period,
                        TimeUnit.MILLISECONDS);

        startTimer(future, dieout);
    }


    private void startTimer(final ScheduledFuture future, int dieout){
        this.schedule(new Runnable() {
            @Override
            public void run() {
                future.cancel(true);
            }
        }, dieout, TimeUnit.SECONDS);
    }

}
