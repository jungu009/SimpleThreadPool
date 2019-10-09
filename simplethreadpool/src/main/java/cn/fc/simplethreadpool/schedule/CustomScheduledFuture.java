package cn.fc.simplethreadpool.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * 用于中断定时器任务
 */
public class CustomScheduledFuture {

    private List<ScheduledFuture> mFutures = new ArrayList<>();

    /**
     * 添加任务
     * @param future 任务关联的future
     */
    public void addFuture(ScheduledFuture future) {
        this.mFutures.add(future);
    }

    /**
     * 中断开启的所有任务
     */
    public void cancelAllTask(){
        for (ScheduledFuture future : mFutures) {
            future.cancel(true);
        }
    }
}
