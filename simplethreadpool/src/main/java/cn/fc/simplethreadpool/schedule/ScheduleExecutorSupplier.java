package cn.fc.simplethreadpool.schedule;

/**
 * 获取ScheduledThreadPoolExecutor的工具类
 * 是一个单例
 */
public class ScheduleExecutorSupplier {
    /*
     * Number of cores to decide the number of threads
     */
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    /**
     * 带计划运行的线程的线程池
     */
    private final CustomScheduledThreadPoolExecutor mScheduledThreadPoolExecutor;

    /*
     * an instance of ScheduleExecutorSupplier
     */
    private static ScheduleExecutorSupplier sInstance;

    /*
     * returns the instance of ScheduleExecutorSupplier
     */
    public static ScheduleExecutorSupplier getInstance() {
        if (sInstance == null) {
            synchronized (ScheduleExecutorSupplier.class) {
                sInstance = new ScheduleExecutorSupplier();
            }
        }
        return sInstance;
    }

    /*
     * constructor for  ScheduleExecutorSupplier
     */
    private ScheduleExecutorSupplier() {
        mScheduledThreadPoolExecutor = //Executors.newScheduledThreadPool(NUMBER_OF_CORES * 2);
                new CustomScheduledThreadPoolExecutor(NUMBER_OF_CORES * 2);
    }

    /**
     * 获取Scheduled线程池
     * @return 线程池
     */
    public  CustomScheduledThreadPoolExecutor forScheduledTask(){
        return mScheduledThreadPoolExecutor;
    }

}
