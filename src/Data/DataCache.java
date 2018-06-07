package Data;

public class DataCache {

    private DataBlock[] blocks;

    public DataCache (int numberOfBlocks) {
        this.blocks = new DataBlock[numberOfBlocks];
    }

    public DataBlock getBlock(int i) {
        return blocks[i];
    }
}
