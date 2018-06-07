package Controller;

import Logic.DualCore;
import Logic.SimpleCore;
import Storage.Context;
import Storage.MainMemory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Simulation {

    private Deque<Context> threadQueue;
    private FileReader fileReader;

    private CyclicBarrier barrier;
    private ReentrantLock dataBus;
    private ReentrantLock instructionsBus;
    private MainMemory mainMemory;

    private DualCore dualCore;
    private SimpleCore simpleCore;

    private int clock;

    public Simulation(){
        this.clock = 0;
        this.mainMemory = new MainMemory();
        this.dataBus = new ReentrantLock();
        this.instructionsBus = new ReentrantLock();
        this.threadQueue = new ArrayDeque<>();
    }

    public void start(){
        this.dualCore = new DualCore(this);
        this.simpleCore = new SimpleCore(this);
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

    public MainMemory getMainMemory() {
        return this.mainMemory;
    }

    public void setMainMemory(MainMemory mainMemory) {
        this.mainMemory = mainMemory;
    }

    public ReentrantLock getDataBus() {
        return this.dataBus;
    }

    public void setDataBus(ReentrantLock dataBus) {
        this.dataBus = dataBus;
    }

    public ReentrantLock getInstructionsBus() {
        return this.instructionsBus;
    }

    public void setInstructionsBus(ReentrantLock instructionsBus) {
        this.instructionsBus = instructionsBus;
    }

    public boolean tryLockInstructionsCacheBlock(){
        return true;
    }

    public void unlockInstructionsCacheBlock(){
    }

    public boolean tryLockDataCacheBlock(boolean isSimpleCore, int blockLabel){
        return true;
    }

    public void unlockDataCacheBlock(boolean isSimpleCore, int blockLabel){
    }
}
