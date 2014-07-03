package com.zippy.ig.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.zippy.ig.common.container.ContainerLeadFurnace;
import com.zippy.ig.common.tileentity.TileEntityLeadFurnace;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

public GuiHandler (){

}

public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
if(ID == 0){
TileEntityLeadFurnace tileEntityFurnace = (TileEntityLeadFurnace) world.getTileEntity(x, y, z);
return new ContainerLeadFurnace(player.inventory, tileEntityFurnace);
}
return null;
}

public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
if(ID == 0){
TileEntityLeadFurnace tileEntityTestContainer = (TileEntityLeadFurnace) world.getTileEntity(x, y, z);
return new guiLeadFurnace(player.inventory, tileEntityTestContainer);
}
return null;
}

}
