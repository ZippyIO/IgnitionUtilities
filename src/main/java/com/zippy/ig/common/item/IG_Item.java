package com.zippy.ig.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.zippy.ig.common.registry.IGRegistry;


public class IG_Item extends Item{

	private String name;
	
	public IG_Item(String name){
		super();
		setUnlocalizedName(name);
		setCreativeTab(IGRegistry.tabCreative);
		setMaxStackSize(64);
		this.name = name;
	}
	
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ignitionutilities:"+name.toLowerCase());
	}
	
	public String getName(){
		return name;
	}
}
