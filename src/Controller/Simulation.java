package Controller;

import Storage.Context;

import java.util.Deque;
import java.util.concurrent.CyclicBarrier;

public class Simulation {

    private Deque<Context> threadQueue;
    private FileReader fileReader;

    private CyclicBarrier barrier;

    private int clock;

    public Simulation(){
        this.clock = 0;
    }

    public void start(){

    }

    public CyclicBarrier getBarrier() {
        return this.barrier;
    }

    public synchronized Context getNextContext(){
        return this.threadQueue.pop();
    }

    public synchronized void addContext(Context context){
        this.threadQueue.push(context);
    }
}
