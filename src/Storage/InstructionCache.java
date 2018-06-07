package Storage;

public class InstructionCache {

    private Block[] blocks;

    public InstructionCache (int numberOfBlocks) {
        this.blocks = new Block[numberOfBlocks];
    }

    public Block getBlock(int i) {
        return blocks[i];
    }

}
