package cn.fc.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.Future;

import cn.fc.simplethreadpool.DefaultExecutorSupplier;

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
    }
}
