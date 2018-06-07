package Logic;

import Controller.Simulation;
import Storage.CacheStatus;
import Storage.Context;
import Storage.DataCache;
import Storage.InstructionCache;

public class Core {

    private DataCache dataCache;
    private InstructionCache instructionCache;

    private boolean isSimpleCore;

    private Simulation simulation;

    private int clock;

    public Core (Simulation simulation, int numberOfBlocks, boolean isSimpleCore) {
        this.simulation = simulation;
        this.dataCache = new DataCache(numberOfBlocks);
        this.instructionCache = new InstructionCache(numberOfBlocks);
        this.clock = 0;
        this.isSimpleCore = isSimpleCore;
    }

    public DataCache getDataCache() {
        return this.dataCache;
    }

    public void setDataCache(DataCache dataCache) {
        this.dataCache = dataCache;
    }

    public InstructionCache getInstructionCache() {
        return this.instructionCache;
    }

    public void setInstructionCache(InstructionCache instructionCache) {
        this.instructionCache = instructionCache;
    }

    public Simulation getSimulation() {
        return this.simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public int getClock() {
        return this.clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public void manageLoadWord(Context context, int destinyRegister, int sourceRegister, int immediate){
        int blockLabel = this.simulation.getMainMemory().getBlockLabelByAddress(context.getRegister(sourceRegister) + immediate);
        if (this.dataCache.getBlock(blockLabel).getLock().tryLock()){
            if (this.dataCache.hasBlock(blockLabel)){
                CacheStatus blockStatus = this.dataCache.getBlock(blockLabel).getBlockStatus();
                if (blockStatus == CacheStatus.Modified || blockStatus == CacheStatus.Shared){
                    context.setRegister(destinyRegister, this.dataCache.getBlock(blockLabel).getData());
                }
                else {
                    if (this.simulation.getDataBus().tryLock()) {
                        if (this.simulation.tryLockDataCacheBlock(this.isSimpleCore, blockLabel)){

                        }
                        else {
                            //TODO: Release everything
                        }
                    }
                    else {
                        //TODO: Release everything
                    }
                }
            }
            else {

            }
        } else {
            //TODO: Release everything
        }
    }

    public void manageStoreWord(){

    }
}
