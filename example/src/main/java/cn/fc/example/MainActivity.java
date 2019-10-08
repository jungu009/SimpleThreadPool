package cn.fc.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import cn.fc.simplethreadpool.DefaultExecutorSupplier;
import cn.fc.simplethreadpool.Priority;
import cn.fc.simplethreadpool.PriorityRunnable;
import cn.fc.simplethreadpool.schedule.ScheduleExecutorSupplier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 普通的带有优先级的线程池
        doSomeTaskAtHighPriority();

        // 带有定时功能的线程池
        doScheduleTask();

    }

    public void doScheduleTask(){
        ScheduleExecutorSupplier.getInstance().forScheduledTask().schedule(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                Log.d("Schedule", "schedule:" + i++);
            }
        }, 1000, 1000, 30);

//        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
//            int i = 0;
//            @Override
//            public void run() {
//                Log.d("Example", "schedule:" + i++);
//            }
//        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }

    /*
     * do some task at high priority
     */
    public void doSomeTaskAtHighPriority(){
        DefaultExecutorSupplier.getInstance().forBackgroundTasks()
                .submit(new PriorityRunnable(Priority.HIGH) {
                    @Override
                    public void run() {
                        // do some background work here at high priority.
                        for(int i = 0; i < 30; i++){
                            Log.d("Priority", "Priority:" + i);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

}
