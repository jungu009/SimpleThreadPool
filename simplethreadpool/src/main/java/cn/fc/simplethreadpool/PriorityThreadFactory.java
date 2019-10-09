package cn.fc.simplethreadpool;

import android.os.Process;

import androidx.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * 生产带有优先级的Thread
 */
public class PriorityThreadFactory implements ThreadFactory {
    private final int mThreadPriority;

    PriorityThreadFactory(int threadPriority) {
        mThreadPriority = threadPriority;
    }

    /**
     * 设置优先级
     * @param runnable 要执行的任务
     * @return Thread
     */
    @Override
    public Thread newThread(@NonNull final Runnable runnable) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(mThreadPriority);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                runnable.run();
            }
        };
        return new Thread(wrapperRunnable);
    }
}
