package com.zippy.ig.common.item;

import com.zippy.ig.common.machine.ISpeeded;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpeedUpgrade extends IG_Item {

	public SpeedUpgrade(String name) {
		super(name);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		if(par3World.getTileEntity(x, y, z) instanceof ISpeeded){
			ISpeeded te = (ISpeeded) par3World.getTileEntity(x, y, z);
			if(te.upgrade()){
				par1ItemStack.splitStack(1);
			return true;
			}
			return false;
		}
		return false;
	}

}
