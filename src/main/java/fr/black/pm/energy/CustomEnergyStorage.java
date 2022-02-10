package fr.black.pm.energy;

import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {

    public CustomEnergyStorage(int capacity, int maxTransfert) { super(capacity, maxTransfert, 0);}

    //Override this if you want
    protected void onEnergyChange(){
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energy = super.receiveEnergy(maxReceive, simulate);
        if(energy > 0 && !simulate) {
            onEnergyChange();
        }
        return energy;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energy = super.extractEnergy(maxExtract, simulate);
        if(energy > 0 && !simulate) {
            onEnergyChange();
        }
        return energy;
    }

    public void setEnergy(int energy){
        this.energy = energy;
        onEnergyChange();
    }

    public void addEnergy(int energy){
        this.energy += energy;
        if(this.energy > getMaxEnergyStored()){
            this.energy = getEnergyStored();
        }
        onEnergyChange();
    }

    public void consumeEnergy(int energy){
        if(!(this.energy <= 0)){
            this.energy -= energy;
            if(this.energy < 0){this.energy = 0;}
            onEnergyChange();
        }
    }

}
