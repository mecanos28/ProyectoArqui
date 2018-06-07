package Storage;

public class InstructionBlock extends Block {

    private int[] values;

    public InstructionBlock() {
        this.values = new int[4];
    }

    public int getValue (int i){
        return this.values[i];
    }

    public void setValue (int i, int value){
        this.values[i] = value;
    }

}
