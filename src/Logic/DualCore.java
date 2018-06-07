package Logic;

import Controller.Simulation;
import Storage.Context;
import Storage.DataCache;
import Storage.InstructionCache;

public class DualCore {

    private Context oldThread;
    private Context newThread;

    private DataCache dataCache;
    private InstructionCache instructionCache;

    private Simulation simulation;

    private int clock;

    public DualCore(Simulation simulation){
        this.simulation = simulation;
        this.dataCache = new DataCache(8);
        this.instructionCache = new InstructionCache(8);
        this.clock = 0;
    }

}
