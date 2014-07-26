package com.zippy.ig.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.zippy.ig.common.container.ContainerLeadFurnace;
import com.zippy.ig.common.container.ContainerMachinePurifier;
import com.zippy.ig.common.container.ContainerMachineRoaster;
import com.zippy.ig.common.container.ContainerPulverizier;
import com.zippy.ig.common.machine.generator.ContainerGenerator;
import com.zippy.ig.common.machine.generator.GuiGenerator;
import com.zippy.ig.common.machine.generator.TileSteamGenerator;
import com.zippy.ig.common.tile.TilePulverizier;
import com.zippy.ig.common.tileentity.TileEntityLeadFurnace;
import com.zippy.ig.common.tileentity.TileEntityMachinePurifier;
import com.zippy.ig.common.tileentity.TileEntityMachineRoaster;
import common.cout970.UltraTech.containers.CutterContainer;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

public GuiHandler (){

}


public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

if(ID == 0){
TileEntityLeadFurnace tileEntityFurnace = (TileEntityLeadFurnace) world.getTileEntity(x, y, z);
return new ContainerLeadFurnace(player.inventory, tileEntityFurnace);
}
if(ID == 1){
TileEntityMachinePurifier tileEntityPurifier = (TileEntityMachinePurifier) world.getTileEntity(x, y, z);
return new ContainerMachinePurifier(player.inventory, tileEntityPurifier);
}
if(ID == 2){
TileEntityMachineRoaster tileEntityRoaster = (TileEntityMachineRoaster) world.getTileEntity(x, y, z);
return new ContainerMachineRoaster(player.inventory, tileEntityRoaster);
}
if(ID == 5){
TilePulverizier pulverizier = (TilePulverizier) world.getTileEntity(x, y, z);
return new ContainerPulverizier(player.inventory, pulverizier);
}
if(ID == 6){
	TileSteamGenerator steamgenerator = (TileSteamGenerator) world.getTileEntity(x, y, z);
return new ContainerGenerator(player.inventory, steamgenerator);
}
return null;
}

public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

if(ID == 0){
TileEntityLeadFurnace tileEntityTestContainer = (TileEntityLeadFurnace) world.getTileEntity(x, y, z);
return new guiLeadFurnace(player.inventory, tileEntityTestContainer);
}
if(ID == 1){
	TileEntityMachinePurifier tileEntityCPurifer = (TileEntityMachinePurifier) world.getTileEntity(x, y, z);
return new guiMachinePurifier(player.inventory, tileEntityCPurifer);
}
if(ID == 2){
	TileEntityMachineRoaster tileEntityCRoaster = (TileEntityMachineRoaster) world.getTileEntity(x, y, z);
return new GuiMachineRoaster(player.inventory, tileEntityCRoaster);
}
if(ID == 5){
	TilePulverizier CPulverizier = (TilePulverizier) world.getTileEntity(x, y, z);
return new GuiPulverizier(player.inventory, CPulverizier);
}
if(ID == 6){
	TileSteamGenerator SGenerator = (TileSteamGenerator) world.getTileEntity(x, y, z);
return new GuiGenerator(player.inventory, SGenerator);
}
return null;
}

}
