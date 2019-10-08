package cn.fc.simplethreadpool.schedule;

public class ScheduleExecutorSupplier {
    /*
     * Number of cores to decide the number of threads
     */
    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    /*
     * thread pool executor for ScheduledThreadPoolExecutor
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

    public  CustomScheduledThreadPoolExecutor forScheduledTask(){
        return mScheduledThreadPoolExecutor;
    }

}
