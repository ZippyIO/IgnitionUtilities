package com.zippy.ig.common.machine;

import IgnitionCore.power.StorageInterface.PowerIO;


public enum MachineData {
	/**
	 * 2 burning tick = 1MeV
	 */
	SteamGenerator	( 3200d	,1	,1d			,1);//1coal => 800Mev
	
	public double cap,use;
	public int tier;
	public PowerIO type;
	
	private MachineData(double cap,int tier,double use,int inter){
		this.cap = cap;
		this.tier = tier;
		this.use = use;
		if(inter == 0)type = PowerIO.Nothing;
		if(inter == 1)type = PowerIO.Output;
		if(inter == 2)type = PowerIO.Storage;
		if(inter == 3)type = PowerIO.Input;
	}
}
