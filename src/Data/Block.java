package Data;

public class Block {
    private int[] values;

    public Block() {
        this.values = new int[4];
    }

    public int getValue (int i){
        return this.values[i];
    }

    public void setValue (int i, int value){
        this.values[i] = value;
    }
}
