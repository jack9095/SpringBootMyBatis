package com.example.demo.utils;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {
    private static ThreadManager instance;
    private ThreadPoolExecutor mThreadPool;
    public static final int PERIOD_LOW = 1;
    public static final int PERIOD_MIDDLE = 2;
    public static final int PERIOD_HIGH = 3;

    private ThreadManager() {
        final long keepAliveTime = 60L;
        final int mThreadCount = 5;
        Comparator<PrioriTask> mCompare = new TaskCompare();
        PriorityBlockingQueue mThreadQueue = new PriorityBlockingQueue<PrioriTask>(mThreadCount, mCompare);
        mThreadPool = new ThreadPoolExecutor(mThreadCount, Integer.MAX_VALUE, keepAliveTime, TimeUnit.SECONDS, mThreadQueue);
    }

    public static ThreadManager getInstance() {
        if (instance == null) {
            instance = ThreadManagerIn.INSTANCE;
        }
        return instance;
    }

    private static class ThreadManagerIn{
        private static final ThreadManager INSTANCE = new ThreadManager();
    }

    public void executorThread(Runnable task, int priority) {
        if (!mThreadPool.isShutdown()) {
            mThreadPool.execute(new PrioriTask(priority, task));
        }
    }

    public void shutDownAll() {
        mThreadPool.shutdownNow();
        instance = null;
    }

    private class PrioriTask implements Runnable {
        private int priori;
        private Runnable task;

        PrioriTask(int priority, Runnable runnable) {
            priori = priority;
            task = runnable;
        }

        int getPriori() {
            return priori;
        }

        public Runnable getTask() {
            return task;
        }

        public void setTask(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            if (task != null) {
                task.run();
            }
        }
    }

    private class TaskCompare implements Comparator<PrioriTask> {

        @Override
        public int compare(PrioriTask lhs, PrioriTask rhs) {
            return rhs.getPriori() - lhs.getPriori();
        }
    }
}
