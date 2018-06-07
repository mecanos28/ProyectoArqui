package Storage;

import java.util.concurrent.locks.ReentrantLock;

public class Block {
    private int[] values;
    private int label;
    private ReentrantLock lock;

    public Block() {
        this.values = new int[4];
        this.lock = new ReentrantLock();
    }

    public int getValue (int i){
        return this.values[i];
    }

    public void setValue (int i, int value){
        this.values[i] = value;
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
