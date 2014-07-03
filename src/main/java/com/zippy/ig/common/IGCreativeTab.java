package com.zippy.ig.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IGCreativeTab extends CreativeTabs
{
	//public static IGCreativeTab tabCreative = new IGCreativeTab("IgnitionUtil");
	
	public ItemStack theIcon;
	
	public IGCreativeTab(String label)
	{
		super(label);
	}
	
	public static void setupIcons()
	{
		IGMod.tabCreative.theIcon = new ItemStack(IGMod.oreCopper);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return StatCollector.translateToLocal("ig.creativetab." + getTabLabel());
    }
	
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return theIcon.getItem();
    }
    
	@Override
    public ItemStack getIconItemStack()
    {
        return theIcon;
    }
}
