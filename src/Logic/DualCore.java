package Logic;

import Controller.Simulation;
import Storage.Context;

public class DualCore extends Core {

    private Context oldThread;
    private Context newThread;

    public DualCore(Simulation simulation){
        super(simulation, 8, false);
        this.oldThread = super.getSimulation().getNextContext();
    }

}
