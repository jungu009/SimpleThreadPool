package cn.fc.simplethreadpool;

/**
 * 带有优先级的Runnable
 */
public class PriorityRunnable implements Runnable {

    /**
     * 该线程的优先级
     */
    private final Priority priority;

    protected PriorityRunnable(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        // nothing to do here.
    }

    /**
     * 获取优先级
     * @return 优先级 {@link Priority}
     */
    Priority getPriority() {
        return priority;
    }

}
