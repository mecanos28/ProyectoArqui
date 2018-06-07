package Logic;

import Controller.Simulation;
import Storage.*;

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
                    this.dataCache.getBlock(blockLabel).getLock().unlock();
                    context.setRegister(destinyRegister, this.dataCache.getBlock(blockLabel).getData());
                    //TODO: Change cycle.
                }
                else {
                    if (this.simulation.getDataBus().tryLock()) {
                        if (this.simulation.tryLockDataCacheBlock(this.isSimpleCore, blockLabel)){
                            if (this.simulation.checkDataBlockOnOtherCache(this.isSimpleCore, blockLabel)){
                                DataBlock blockFromOtherCache = this.simulation.getDataBlockFromOtherCache(this.isSimpleCore, blockLabel);
                                if (blockFromOtherCache.getBlockStatus() == CacheStatus.Modified){
                                    //TODO: Copy to cache
                                    this.simulation.saveDataBlockToMainMemory(blockFromOtherCache, blockLabel);
                                    this.simulation.changeDataBlockStatusFromOtherCache(this.isSimpleCore, blockLabel, CacheStatus.Shared);
                                    this.simulation.unlockDataCacheBlock(this.isSimpleCore, blockLabel);
                                }
                                else {
                                    this.simulation.unlockDataCacheBlock(this.isSimpleCore, blockLabel);
                                    //TODO: Copy from memory
                                }
                            }
                            else {
                                this.simulation.unlockDataCacheBlock(this.isSimpleCore, blockLabel);
                                //TODO: Copy from memory
                            }
                        }
                        else {
                            //TODO: Release everything
                        }
                    }
                    else {
                        //TODO: Release everything
                    }
                    this.dataCache.getBlock(blockLabel).getLock().unlock();
                    context.setRegister(destinyRegister, this.dataCache.getBlock(blockLabel).getData());
                    //TODO: Change cycle.
                }
            }
            else {
                if (this.simulation.getDataBus().tryLock()) {
                    if (this.dataCache.getBlock(blockLabel).getBlockStatus() == CacheStatus.Modified){
                        this.simulation.saveDataBlockToMainMemory(this.dataCache.getBlock(blockLabel), blockLabel);
                        //TODO: 40 cycles
                    }
                    if (this.simulation.tryLockDataCacheBlock(this.isSimpleCore, blockLabel)){
                        if (this.simulation.checkDataBlockOnOtherCache(this.isSimpleCore, blockLabel)){
                            DataBlock blockFromOtherCache = this.simulation.getDataBlockFromOtherCache(this.isSimpleCore, blockLabel);
                            if (blockFromOtherCache.getBlockStatus() == CacheStatus.Shared){
                                this.simulation.unlockDataCacheBlock(this.isSimpleCore, blockLabel);
                                //TODO: Copy from memory
                            }
                            else if (blockFromOtherCache.getBlockStatus() == CacheStatus.Modified){
                                //TODO: Copy to cache
                                this.simulation.saveDataBlockToMainMemory(blockFromOtherCache, blockLabel);
                                this.simulation.changeDataBlockStatusFromOtherCache(this.isSimpleCore, blockLabel, CacheStatus.Shared);
                                this.simulation.unlockDataCacheBlock(this.isSimpleCore, blockLabel);
                            }
                            else {
                                this.simulation.unlockDataCacheBlock(this.isSimpleCore, blockLabel);
                                //TODO: Copy from memory
                            }
                        }
                        else {
                            this.simulation.unlockDataCacheBlock(this.isSimpleCore, blockLabel);
                            //TODO: Copy from memory
                        }
                    }
                    else {
                        this.simulation.getDataBus().unlock();
                        this.dataCache.getBlock(blockLabel).getLock().unlock();
                        //TODO: Release everything
                    }
                }
                else {
                    this.dataCache.getBlock(blockLabel).getLock().unlock();
                    //TODO: Cycle and start over
                }
                this.dataCache.getBlock(blockLabel).getLock().unlock();
                context.setRegister(destinyRegister, this.dataCache.getBlock(blockLabel).getData());
                //TODO: Cycle
            }
        } else {
            //TODO: Cycle and start over
        }
    }

    public void manageStoreWord(){

    }
}
