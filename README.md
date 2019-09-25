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
	        implementation 'com.github.jungu009:SimpleThreadPool:v0.2'
	}
```
```maven
        <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
	<dependency>
	    <groupId>com.github.jungu009</groupId>
	    <artifactId>SimpleThreadPool</artifactId>
	    <version>v0.2</version>
	</dependency>
```

2. 示例代码

```java
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
```
