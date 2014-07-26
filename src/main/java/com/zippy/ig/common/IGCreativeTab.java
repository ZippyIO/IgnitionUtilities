package com.zippy.ig.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.zippy.ig.common.registry.IGItems;
import com.zippy.ig.common.registry.IGRegistry;

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
		IGRegistry.tabCreative.theIcon = new ItemStack(IGItems.Lapanite);
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
