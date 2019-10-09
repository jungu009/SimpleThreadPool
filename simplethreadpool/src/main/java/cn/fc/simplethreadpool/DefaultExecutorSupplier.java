package cn.fc.simplethreadpool;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 单例
 * 获取线程池的工具类
 */
public class DefaultExecutorSupplier {
    /*
     * Number of cores to decide the number of threads
     */
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    /**
     * thread pool executor for background tasks
     */
    private final PriorityThreadPoolExecutor mForBackgroundTasks;
    /**
     * thread pool executor for light weight background tasks
     */
    private final ThreadPoolExecutor mForLightWeightBackgroundTasks;
    /**
     * thread pool executor for main thread tasks
     */
    private final Executor mMainThreadExecutor;
    /**
     * an instance of DefaultExecutorSupplier
     */
    private static DefaultExecutorSupplier sInstance;

    /**
     * returns the instance of DefaultExecutorSupplier
     */
    public static DefaultExecutorSupplier getInstance() {
        if (sInstance == null) {
            synchronized (DefaultExecutorSupplier.class) {
                sInstance = new DefaultExecutorSupplier();
            }
        }
        return sInstance;
    }

    /**
     * constructor for  DefaultExecutorSupplier
     */
    private DefaultExecutorSupplier() {

        // setting the thread factory
        ThreadFactory backgroundPriorityThreadFactory = new
                PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        // setting the thread pool executor for mForBackgroundTasks;
        mForBackgroundTasks = new PriorityThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                backgroundPriorityThreadFactory
        );

        // setting the thread pool executor for mForLightWeightBackgroundTasks;
        mForLightWeightBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        // setting the thread pool executor for mMainThreadExecutor;
        mMainThreadExecutor = new MainThreadExecutor();
    }

    /**
     * returns the thread pool executor for background task
     * @return 返回具有优先级的线程池
     */
    public ThreadPoolExecutor forBackgroundTasks () {
        return mForBackgroundTasks;
    }

    /**
     * returns the thread pool executor for light weight background task
     * @return 返回普通的线程池
     */
    public ThreadPoolExecutor forLightWeightBackgroundTasks () {
        return mForLightWeightBackgroundTasks;
    }

    /**
     * returns the thread pool executor for main thread task
     * @return 返回主线程
     */
    public Executor forMainThreadTasks () {
        return mMainThreadExecutor;
    }
}
