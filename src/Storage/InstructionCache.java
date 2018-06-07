package Storage;

public class InstructionCache {

    private Block[] blocks;

    public InstructionCache (int numberOfBlocks) {
        this.blocks = new Block[numberOfBlocks];
    }

    public Block getBlock(int i) {
        return blocks[i];
    }

    public boolean hasBlock(int label){
        return (this.blocks[this.calculateIndexByLabel(label)].getLabel() == label);
    }

    private int calculateIndexByLabel(int label){
        //TODO: Calculate index of the block by it's label
        return 0;
    }

}
