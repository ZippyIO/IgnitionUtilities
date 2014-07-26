package com.zippy.ig.common.registry;

import com.zippy.ig.common.IGMod;
import com.zippy.ig.common.leadFurnace;
import com.zippy.ig.common.block.blockPulverizer;
import com.zippy.ig.common.block.machinePurifier;
import com.zippy.ig.common.block.oreColanite;
import com.zippy.ig.common.block.oreCopper;
import com.zippy.ig.common.block.oreGoldanite;
import com.zippy.ig.common.block.oreIronite;
import com.zippy.ig.common.block.oreLapanite;
import com.zippy.ig.common.block.oreLead;
import com.zippy.ig.common.block.oreRedanite;
import com.zippy.ig.common.block.oreSilver;
import com.zippy.ig.common.block.oreTin;
import com.zippy.ig.common.block.ore.oreIridium;
import com.zippy.ig.common.block.ore.orePalladium;
import com.zippy.ig.common.block.ore.oreTungsten;
import com.zippy.ig.common.block.ore.oreUranium;
import com.zippy.ig.common.machine.generator.SteamGenerator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class IGBlocks {
	    /*
	     * =================
	     * ===== Ores ======
	     * =================
	     */
		
	    /*
	     * -------------------
	     * --- Base Metals ---
	     * -------------------
	     */
		public static Block oreCopper;
		public static Block oreTin;
		public static Block oreSilver;
		public static Block oreLead;
		public static Block oreGoldanite;
		public static Block oreIronite;
		public static Block oreLapanite;
		public static Block oreRedanite;
		
		/*
	     * ----------------------
	     * --- Utility Metals ---
	     * ----------------------
	     */
		public static Block oreColanite;
		

		/*
	     * ----------------------
	     * --- Special Metals ---
	     * ----------------------
	     */
		public static Block oreIridium;
		public static Block oreTungsten;
		public static Block oreUranium; 
		public static Block orePalladium;
		
	    /*
	     * ====================
	     * === Metal Blocks ===
	     * ====================
	     */
		
		
	    /*
	     * ================
	     * === Machines ===
	     * ================
	     */
		public static Block leadFurnace;
		public static Block leadFurnaceActive;
		public static Block machinePurifier;
		public static Block machinePurifierActive;
		public static Block machineRoaster;
		public static Block machineRoasterActive;
		public static Block machine;
		public static Block machineActive;
		public static Block storage;
		public static Block EPulverizer;
		/*
	     * ------------------------
	     * --- Powered Machines ---
	     * ------------------------
	     */
		public static Block SteamGenerator;
	
	public static void preInit() {

		oreCopper = new oreCopper(Material.rock).setHardness(1.5F).setBlockName("oreCopper").setCreativeTab(IGRegistry.tabCreative).setBlockTextureName("oreCopper");
		oreTin = new oreTin(Material.rock).setHardness(1.5F).setBlockName("oreTin").setCreativeTab(IGRegistry.tabCreative).setBlockTextureName("oreTin");
		oreSilver = new oreSilver(Material.rock).setHardness(1.5F).setBlockName("oreSilver").setCreativeTab(IGRegistry.tabCreative).setBlockTextureName("oreSilver");
		oreLead = new oreLead(Material.rock).setHardness(1.5F).setBlockName("oreLead").setCreativeTab(IGRegistry.tabCreative).setBlockTextureName("oreLead");
		oreColanite = new oreColanite(Material.rock).setBlockName("oreColanite").setBlockTextureName("oreColanite").setCreativeTab(IGRegistry.tabCreative);
		oreGoldanite = new oreGoldanite(Material.rock).setBlockName("oreGoldanite").setBlockTextureName("oreGoldanite").setHardness(1.5F).setCreativeTab(IGRegistry.tabCreative);
		oreIronite = new oreIronite(Material.rock).setBlockName("oreIronite").setBlockTextureName("oreIronite").setHardness(1.5F).setCreativeTab(IGRegistry.tabCreative);
		oreLapanite = new oreLapanite(Material.rock).setBlockName("oreLapanite").setBlockTextureName("oreLapanite").setHardness(1.5F).setCreativeTab(IGRegistry.tabCreative);
		oreRedanite = new oreRedanite(Material.rock).setBlockName("oreRedanite").setBlockTextureName("oreRedanite").setHardness(1.5F).setCreativeTab(IGRegistry.tabCreative);
		
		oreIridium = new oreIridium(Material.rock).setBlockName("oreIridium").setBlockTextureName("oreIridium").setCreativeTab(IGRegistry.tabCreative);
		oreTungsten = new oreTungsten(Material.rock).setBlockName("oreTungsten").setBlockTextureName("oreTungsten").setCreativeTab(IGRegistry.tabCreative);
		oreUranium = new oreUranium(Material.rock).setBlockName("oreUranium").setBlockTextureName("oreUranium").setCreativeTab(IGRegistry.tabCreative);
		orePalladium = new orePalladium(Material.rock).setBlockName("orePalladium").setBlockTextureName("orePalladium").setCreativeTab(IGRegistry.tabCreative);
		
		leadFurnace = new leadFurnace(false).setBlockName("leadFurnace").setHardness(1.5F).setCreativeTab(IGRegistry.tabCreative);
		leadFurnaceActive = new leadFurnace(true).setBlockName("leadFurnaceActive").setHardness(1.5F);
		
		machinePurifier = new machinePurifier(false).setBlockName("machinePurifier").setHardness(1.5F).setCreativeTab(IGRegistry.tabCreative);
		machinePurifierActive = new machinePurifier(true).setBlockName("machinePurifierActive").setHardness(1.5F);
		
		EPulverizer = new blockPulverizer().setBlockName("pulverizier");

		SteamGenerator = new SteamGenerator(Material.iron).setBlockName("SteamGenerator").setCreativeTab(IGRegistry.tabCreative);
		
		registerBlock(oreCopper);
		registerBlock(oreTin);
		registerBlock(oreSilver);
		registerBlock(oreLead);
		registerBlock(oreColanite);
		registerBlock(oreGoldanite);
		registerBlock(oreIronite);
		registerBlock(oreLapanite);
		registerBlock(oreRedanite);
		registerBlock(oreIridium);
		registerBlock(oreTungsten);
		registerBlock(oreUranium);
		registerBlock(orePalladium);
		
		registerBlock(leadFurnace);
		registerBlock(leadFurnaceActive);
		registerBlock(machinePurifier);
		registerBlock(machinePurifierActive);
		registerBlock(EPulverizer);
		registerBlock(SteamGenerator);
		
		oreCopper.setHarvestLevel("pickaxe", 1);
		oreTin.setHarvestLevel("pickaxe", 1);
		oreSilver.setHarvestLevel("pickaxe", 2);
		oreLead.setHarvestLevel("pickaxe", 2);
		oreIronite.setHarvestLevel("pickaxe", 1);
		oreGoldanite.setHarvestLevel("pickaxe", 2);
		oreLapanite.setHarvestLevel("pickaxe", 2);
		oreRedanite.setHarvestLevel("pickaxe", 2);
		
		OreDictionary.registerOre("oreCopper", oreCopper);
		OreDictionary.registerOre("oreTin", oreTin);
		OreDictionary.registerOre("oreSilver", oreSilver);
		OreDictionary.registerOre("oreLead", oreLead);
		OreDictionary.registerOre("oreIron", oreIronite);
		OreDictionary.registerOre("oreGold", oreGoldanite);
		OreDictionary.registerOre("oreRedstone", oreRedanite);
		OreDictionary.registerOre("oreLapis", oreLapanite);
		
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
	
	private static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
	
	private static void registerBlock(Block block, Class itemClass)
	{
		GameRegistry.registerBlock(block, itemClass, block.getUnlocalizedName());
	}
}
