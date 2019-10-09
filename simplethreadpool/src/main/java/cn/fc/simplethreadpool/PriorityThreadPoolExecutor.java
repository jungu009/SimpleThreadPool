package cn.fc.simplethreadpool;

import androidx.annotation.NonNull;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 带优先级的线程池
 */
public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {

    PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                               TimeUnit unit, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit,new PriorityBlockingQueue<Runnable>(), threadFactory);
    }

    /**
     *
     * @param task 要执行的任务
     * @return 一个FutureTask
     */
    @Override
    public @NonNull Future<?> submit(Runnable task) {
        PriorityFutureTask futureTask = new PriorityFutureTask((PriorityRunnable) task);
        execute(futureTask);
        return futureTask;
    }

    private static final class PriorityFutureTask extends FutureTask<PriorityRunnable>
            implements Comparable<PriorityFutureTask> {
        private final PriorityRunnable priorityRunnable;

        PriorityFutureTask(PriorityRunnable priorityRunnable) {
            super(priorityRunnable, null);
            this.priorityRunnable = priorityRunnable;
        }

        /*
         * compareTo() method is defined in interface java.lang.Comparable and it is used
         * to implement natural sorting on java classes. natural sorting means the the sort
         * order which naturally applies on object e.g. lexical order for String, numeric
         * order for Integer or Sorting employee by there ID etc. most of the java core
         * classes including String and Integer implements CompareTo() method and provide
         * natural sorting.
         */
        @Override
        public int compareTo(PriorityFutureTask other) {
            Priority p1 = priorityRunnable.getPriority();
            Priority p2 = other.priorityRunnable.getPriority();
            return p2.ordinal() - p1.ordinal();
        }
    }

}
