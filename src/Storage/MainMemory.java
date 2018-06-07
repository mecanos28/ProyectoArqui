package Storage;

public class MainMemory {

    private Block[] blocks;

    public MainMemory () {
        this.blocks = new Block[64];
    }

    public Block getBlock(int i) {
        return blocks[i];
    }

}
