package cn.fc.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.Future;

import cn.fc.simplethreadpool.DefaultExecutorSupplier;
import cn.fc.simplethreadpool.Priority;
import cn.fc.simplethreadpool.PriorityRunnable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Future future = DefaultExecutorSupplier.getInstance().forBackgroundTasks().submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        future.cancel(true);

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        doSomeTaskAtHighPriority();
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
                    }
                });
    }

}
