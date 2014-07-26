package com.zippy.ig.common.registry;

import com.zippy.ig.common.IGCreativeTab;
import com.zippy.ig.common.gui.GuiHandler;
import com.zippy.ig.common.machine.generator.TileSteamGenerator;
import com.zippy.ig.common.tile.TilePulverizier;
import com.zippy.ig.common.tileentity.TileEntityLeadFurnace;
import com.zippy.ig.common.tileentity.TileEntityMachinePurifier;
import com.zippy.ig.common.tileentity.TileEntityMachineRoaster;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;

public class IGRegistry {
	
	
	public static SimpleNetworkWrapper snw;
	
	public static IGCreativeTab tabCreative = new IGCreativeTab("IgnitionUtil");
	public static void preInit() {
	
	
	GuiHandler GuiHandler = new GuiHandler();
	

	GameRegistry.registerTileEntity(TileEntityLeadFurnace.class, "TileEntityLeadFurnace");
	GameRegistry.registerTileEntity(TileEntityMachinePurifier.class, "TileEntityMachinePurifier");
	GameRegistry.registerTileEntity(TileEntityMachineRoaster.class, "TileEntityMachineRoaster");
	GameRegistry.registerTileEntity(TilePulverizier.class, "TileEntityPulverizier");
	GameRegistry.registerTileEntity(TileSteamGenerator.class, "TileEntitySteamGenerator");
	
	}
	
	public void preIni() {

	}
	

}
