package com.zippy.ig.common.gui;

import mekanism.client.gui.GuiElectricMachine;
import mekanism.client.gui.GuiProgress.ProgressBar;
import net.minecraft.entity.player.InventoryPlayer;

import com.zippy.ig.common.tileentity.TileEntityMachineRoaster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMachineRoaster extends GuiElectricMachine
{
	public GuiMachineRoaster(InventoryPlayer inventory, TileEntityMachineRoaster tentity)
	{
		super(inventory, tentity);
	}
	
	@Override
	public ProgressBar getProgressType()
	{
		return ProgressBar.CRUSH;
	}
}
