package Storage;

public class DataBlock {

    private int data;
    private CacheStatus blockStatus;

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
}
