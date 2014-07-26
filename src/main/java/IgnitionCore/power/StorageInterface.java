package IgnitionCore.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class StorageInterface extends PowerInterface{

	private double Capacity = 0.0D;
	private double Charge = 0.0D;
	public PowerTier tier;
	public PowerIO configIO = PowerIO.Nothing;

	public StorageInterface(TileEntity p,double capacity,int tier){
		super(p);
		Capacity = capacity;
		this.tier = PowerTier.getTier(tier);
	}

	public void MachineUpdate(){
		if(getParent().getWorldObj().isRemote)return;
		super.MachineUpdate();
		if(configIO == StorageInterface.PowerIO.Output || configIO == StorageInterface.PowerIO.Storage){
			this.emptyMachine();
		}else if(configIO == StorageInterface.PowerIO.Input){
			this.fillMachine();
		}
	}

	public void emptyMachine(){
		if(getNetwork() == null)return;
		for(StorageInterface b: getNetwork().getMachines()){
			if(b.configIO != StorageInterface.PowerIO.Output)
				PowerUtils.MoveCharge(((IPowerConductor)this.getParent()), ((IPowerConductor)b.getParent()));
		}
	}

	public void fillMachine(){
		if(getNetwork() == null)return;
		for(StorageInterface b: getNetwork().getMachines()){
			if(b.configIO == StorageInterface.PowerIO.Output || b.configIO == StorageInterface.PowerIO.Storage)
				PowerUtils.MoveCharge(((IPowerConductor)b.getParent()), ((IPowerConductor)this.getParent()));
		}
	}

	@Override
	public double addCharge(double amount) {
		Charge += amount;
		if(Charge > getCapacity()){
			double aux = getCapacity() - Charge;
			Charge = getCapacity();
			return aux;
		}if(Charge < 0)Charge = 0;
		return 0;
	}

	@Override
	public void removeCharge(double amount) {
		if(Charge-amount >= 0){
			Charge -= amount;	
		}else{
			Charge = 0;
		}
		if(Charge < 0)Charge = 0;
	}

	public void setCharge(int value) {
		Charge = value;
	}

	@Override
	public double getCharge() {return Charge;}

	@Override
	public double getCapacity() {return Capacity;}

	@Override
	public double getFlow() {return tier.getFlow();}

	public void writeToNBT(NBTTagCompound nbt){
		nbt.setDouble("Charge", Charge);
		nbt.setDouble("Capacity", Capacity);
		if(tier != null)nbt.setInteger("Tier", PowerTier.getPosition(tier));
		if(configIO != null)nbt.setInteger("IO", configIO.ordinal());
	}

	public void readFromNBT(NBTTagCompound nbt){
		Charge = nbt.getDouble("Charge");
		Capacity = nbt.getDouble("Capacity");
		tier = PowerTier.getTier(nbt.getInteger("Tier"));
		configIO = PowerIO.values()[nbt.getInteger("IO")];
	}

	public static enum PowerIO{
		Output,
		Input,
		Nothing,
		Storage;
	}

	public void setChargeDeci(int value) {
		int c = (int) getCharge();
		Charge = (c+((double) value/10));
	}

	
}

