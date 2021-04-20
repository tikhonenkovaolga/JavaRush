package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread thread;
    State state;
    public LoggingStateThread(Thread thread){
        this.thread = thread;
        state = thread.getState();
        System.out.println(state);
        setDaemon(true);
    }

    @Override
    public void run() {
        thread.run();
        while (!thread.isInterrupted()){
            if (thread.getState() != state){
                System.out.println(thread.getState());
                state = thread.getState();
            if (state == State.TERMINATED){
                break;
            }
            }
        }
        }


}
