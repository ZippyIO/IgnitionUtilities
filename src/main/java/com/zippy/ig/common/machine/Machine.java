package com.zippy.ig.common.machine;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import IgnitionCore.misc.SyncTile;
import IgnitionCore.power.CableType;
import IgnitionCore.power.IPower;
import IgnitionCore.power.IPowerConductor;
import IgnitionCore.power.PowerExchange;
import IgnitionCore.power.PowerInterface;
import IgnitionCore.power.PowerNetwork;
import IgnitionCore.power.StorageInterface;
import IgnitionCore.power.StorageInterface.PowerIO;
import cofh.api.energy.IEnergyHandler;

public class Machine extends SyncTile implements IPowerConductor,IPower,IEnergyHandler{

	public StorageInterface cond;
	public static PowerExchange pe = new PowerExchange();
	
	public Machine(double capacity,int tier,PowerIO t){
		super();
		cond = new StorageInterface(this, capacity, tier);
		cond.configIO = t;
	}
	
	public Machine(double capacity,int tier){
		super();
		cond = new StorageInterface(this, capacity, tier);
		cond.configIO = PowerIO.Nothing;
	}
	
	public Machine(double capacity,int tier,PowerIO t,boolean a){
		super();
		cond = new StorageInterface(this, capacity, tier){
			public CableType getConnectionType(ForgeDirection side){
				return ((Machine) getParent()).getConection(side);
			}
			public boolean isConnectableSide(ForgeDirection side ,CableType conection){
				return ((Machine) getParent()).isConnectableSide(side, conection);
			}
		};
		cond.configIO = t;
	}
	
	public Machine(MachineData a){
		this(a.cap,a.tier,a.type);
	}
	
	public Machine(MachineData a,boolean b){
		this(a.cap,a.tier,a.type,true);
	}

	//only for special connection
	public boolean isConnectableSide(ForgeDirection side, CableType conection) {
		return true;
	}
	//only for special connection
	public CableType getConection(ForgeDirection side) {
		return CableType.BLOCK;
	}

	@Override
	public PowerInterface getPower() {
		return cond;
	}

	public void updateEntity(){
		super.updateEntity();
		cond.MachineUpdate();
	}
	
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		cond.readFromNBT(nbt);
	}
	
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		cond.writeToNBT(nbt);
	}

	
	//methods for old energy system
	@Override
	public double addCharge(double amount) {
		return cond.addCharge(amount);
	}

	@Override
	public void removeCharge(double amount) {
		cond.removeCharge(amount);
	}

	@Override
	public double getCharge() {
		return cond.getCharge();
	}

	@Override
	public double getCapacity() {
		return cond.getCapacity();
	}

	@Override
	public double getFlow() {
		return cond.getFlow();
	}

	public double maxFlow() {
		return getFlow();
	}

	public PowerNetwork getNetwork(){
		return cond.getNetwork();
	}

	//redstone flux

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return this.isConnectableSide(from, CableType.BLOCK);
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		if(simulate){
			double cap = getCapacity()-getCharge();
			return (int) Math.min(maxReceive, pe.MeVtoRF(cap));
		}else{
			double old = getCharge();
			addCharge(pe.RFtoMev(maxReceive));
			double spent = getCharge()-old;
			return pe.MeVtoRF(spent);
		}	
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return pe.MeVtoRF(getCharge());
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return pe.MeVtoRF(getCapacity());
	}
}

