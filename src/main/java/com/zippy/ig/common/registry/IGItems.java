package com.zippy.ig.common.registry;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import com.zippy.ig.common.Colanite;
import com.zippy.ig.common.IGMod;
import com.zippy.ig.common.item.SpeedUpgrade;
import com.zippy.ig.common.item.ingot.Lapanite;
import com.zippy.ig.common.item.ingot.ingotBronze;
import com.zippy.ig.common.item.ingot.ingotCopper;
import com.zippy.ig.common.item.ingot.ingotLead;
import com.zippy.ig.common.item.ingot.ingotSilver;
import com.zippy.ig.common.item.ingot.ingotTin;

import cpw.mods.fml.common.registry.GameRegistry;

public class IGItems {

	public static Item ingotCopper;
	public static Item ingotTin;
	public static Item ingotSilver;
	public static Item ingotLead;
	public static Item ingotBronze;
	public static Item Colanite;
	public static Item Lapanite;
	public static Item SpeedUpgrade;
	
	public static void preInit() {
		

		
	ingotCopper = new ingotCopper().setUnlocalizedName("ingotCopper").setTextureName("ingotCopper").setCreativeTab(IGRegistry.tabCreative);
    ingotTin = new ingotTin().setUnlocalizedName("ingotTin").setTextureName("ingotTin").setCreativeTab(IGRegistry.tabCreative);
    ingotSilver = new ingotSilver().setUnlocalizedName("ingotSilver").setTextureName("ingotSilver").setCreativeTab(IGRegistry.tabCreative);
	ingotLead = new ingotLead().setUnlocalizedName("ingotLead").setTextureName("ingotLead").setCreativeTab(IGRegistry.tabCreative);
	ingotBronze = new ingotBronze().setUnlocalizedName("ingotBronze").setTextureName("ingotBronze").setCreativeTab(IGRegistry.tabCreative);
	Colanite = new Colanite().setUnlocalizedName("Colanite").setTextureName("Colanite").setCreativeTab(IGRegistry.tabCreative);
	Lapanite = new Lapanite().setUnlocalizedName("Lapanite").setTextureName("Lapanite").setCreativeTab(IGRegistry.tabCreative);

	SpeedUpgrade = new SpeedUpgrade("SpeedUpgrade");
	
	registerItem(ingotCopper);
	registerItem(ingotTin);
	registerItem(ingotSilver);
	registerItem(ingotLead);
	registerItem(ingotBronze);
	registerItem(Colanite);
	registerItem(Lapanite);
	
	registerItem(SpeedUpgrade);
	
	OreDictionary.registerOre("ingotCopper", ingotCopper);
	OreDictionary.registerOre("ingotTin", ingotTin);
	OreDictionary.registerOre("ingotSilver", ingotSilver);
	OreDictionary.registerOre("ingotLead", ingotLead);
	OreDictionary.registerOre("ingotBronze", ingotBronze);
	OreDictionary.registerOre("dyeBlue", Lapanite);
				

	}

	public static void initialize() {

		loadItems();
	}

	private static void loadItems() {

	}
	
	private static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
}
