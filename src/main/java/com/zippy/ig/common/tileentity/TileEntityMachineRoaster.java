package com.zippy.ig.common.tileentity;

import java.util.Map;

import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.tile.TileEntityElectricMachine;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMachineRoaster extends TileEntityElectricMachine {

	public float roasterMatrix = 0;
	
	public TileEntityMachineRoaster(String soundPath, String name,
			double perTick, int ticksRequired, double maxEnergy) {
		super("Crusher.ogg", "Roaster", 2, 200, 20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(worldObj.isRemote)
		{
			if(roasterMatrix < 6)
			{
				roasterMatrix+=0.2F;
			}
			else {
				roasterMatrix = 0;
			}
		}
	}

	public float getMatrix()
	{
		float matrix = 0;

		if(roasterMatrix <= 3)
		{
			return roasterMatrix;
		}
		else {
			return 3 - (roasterMatrix-3);
		}
	}

	@Override
	public Map getRecipes()
	{
		return Recipe.CRUSHER.get();
	}

	@Override
	public float getVolumeMultiplier()
	{
		return 0.5F;
	}
}
