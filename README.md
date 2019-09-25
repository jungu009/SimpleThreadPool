# SimpleThreadPool

### 简介
一个简单的线程池

参考：[Using ThreadPoolExecutor in Android](https://blog.mindorks.com/threadpoolexecutor-in-android-8e9d22330ee3)

### 如何使用

[![](https://jitpack.io/v/jungu009/SimpleThreadPool.svg)](https://jitpack.io/#jungu009/SimpleThreadPool)

1. 导入库
```gradle
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  dependencies {
	        implementation 'com.github.jungu009:SimpleThreadPool:Tag'
	}
```
2. 示例代码

```java
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
```

