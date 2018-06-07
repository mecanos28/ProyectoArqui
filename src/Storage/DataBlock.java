package Storage;

import java.util.concurrent.locks.ReentrantLock;

public class DataBlock {

    private int data;
    private CacheStatus blockStatus;
    private int label;
    private ReentrantLock lock;

    public DataBlock() {
        this.blockStatus = CacheStatus.Invalid;
        this.data = 0;
    }

    public CacheStatus getBlockStatus() {
        return this.blockStatus;
    }

    public void setBlockStatus(CacheStatus blackStatus){
        this.blockStatus = blockStatus;
    }

    public int getData (){
        return this.data;
    }

    public void setData (int value){
        this.data = value;
    }

    public int getLabel() {
        return this.label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public ReentrantLock getLock() {
        return this.lock;
    }
}
