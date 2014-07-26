package com.zippy.ig.common.block;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class storage extends Block implements IEnergyStorage {


	protected storage(Material p_i45394_1_) {
		super(p_i45394_1_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergyStored() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyStored() {
		// TODO Auto-generated method stub
		return 0;
	}

}
