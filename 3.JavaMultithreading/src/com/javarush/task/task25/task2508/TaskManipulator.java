package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread thread;
    private String threadName;
    public TaskManipulator(String threadName) {
        this.threadName = threadName;

    }

    public TaskManipulator() {

    }


    @Override
    public void run() {
        while (!thread.isInterrupted()) {
            System.out.println(thread.getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                thread.interrupt();
                break;
            }
        }

    }

    @Override
    public void start(String threadName) {
        thread = new Thread(this, threadName);

        thread.start();

    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
