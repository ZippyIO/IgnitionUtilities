package com.zippy.ig.common;

import com.zippy.ig.common.block.oreCopper;
import com.zippy.ig.common.block.oreLead;
import com.zippy.ig.common.block.oreSilver;
import com.zippy.ig.common.block.oreTin;
import com.zippy.ig.common.eventmanager.EventManager;
import com.zippy.ig.common.item.ingot.ingotBronze;
import com.zippy.ig.common.item.ingot.ingotCopper;
import com.zippy.ig.common.item.ingot.ingotLead;
import com.zippy.ig.common.item.ingot.ingotSilver;
import com.zippy.ig.common.item.ingot.ingotTin;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = IGMod.modid, name = "IgnitionUtilities Mod", version = "0.1-MC1.7.2")
public class IGMod
{
	/// Icons/Textures are temporary \\\
	
	
	/// Class files for each part of this made will be done in a latter commit, 
	/// Items & Blocks will be split into other class files along with other things for a clean main class file.
	public static final String modid = "ignitionutilities";

	@SidedProxy(clientSide = "com.zippy.ig.common.IGClientProxy", serverSide = "com.zippy.ig.common.IGCommonProxy")
	public static IGCommonProxy proxy;
	
	///| Blocks |\\\
	
	// Ores ///
	public static Block oreCopper;
	public static Block oreTin;
	public static Block oreSilver;
	public static Block oreLead;
	
	
	///| Items |\\\
	
	/// Ingots \\\
	public static Item ingotCopper;
	public static Item ingotTin;
	public static Item ingotSilver;
	public static Item ingotLead;
	public static Item ingotBronze;
	
	/// Mod Handlers/EventManagers \\\
	EventManager eventmanager = new EventManager();
	public static IGCreativeTab tabCreative = new IGCreativeTab("IgnitionUtil");

	
	@Mod.Instance("IgnitionUtilities")
	public static IGMod instance;
	
	@Mod.EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		///| Blocks |\\\
		
		/// Ores |\\\
		oreCopper = new oreCopper(Material.rock).setHardness(1.5F).setBlockName("oreCopper").setCreativeTab(IGMod.tabCreative).setBlockTextureName("oreCopper");
		oreTin = new oreTin(Material.rock).setHardness(1.5F).setBlockName("ig:oreTin").setCreativeTab(IGMod.tabCreative).setBlockTextureName("oreTin");
		oreSilver = new oreSilver(Material.rock).setHardness(1.5F).setBlockName("ig:oreSilver").setCreativeTab(IGMod.tabCreative).setBlockTextureName("oreSilver");
		oreLead = new oreLead(Material.rock).setHardness(1.5F).setBlockName("ig:oreLead").setCreativeTab(IGMod.tabCreative).setBlockTextureName("oreLead");
		
		///| Items |\\\
		
		/// Ingots \\\
		ingotCopper = new ingotCopper().setUnlocalizedName("ingotCopper").setTextureName("ingotCopper").setCreativeTab(IGMod.tabCreative);
		ingotTin = new ingotTin().setUnlocalizedName("ingotTin").setTextureName("ingotTin").setCreativeTab(IGMod.tabCreative);
		ingotSilver = new ingotSilver().setUnlocalizedName("ingotSilver").setTextureName("ingotSilver").setCreativeTab(IGMod.tabCreative);
		ingotLead = new ingotLead().setUnlocalizedName("ingotLead").setTextureName("ingotLead").setCreativeTab(IGMod.tabCreative);
		ingotBronze = new ingotBronze().setUnlocalizedName("ingotBronze").setTextureName("ingotBronze").setCreativeTab(IGMod.tabCreative);
		
		///| Register Blocks |\\\
		
		/// Register Ores ///
		registerBlock(oreCopper);
		registerBlock(oreTin);
		registerBlock(oreSilver);
		registerBlock(oreLead);
		
		///| Register Items |\\\
		
		/// Ingots \\\
		registerItem(ingotCopper);
		registerItem(ingotTin);
		registerItem(ingotSilver);
		registerItem(ingotLead);
		registerItem(ingotBronze);
		
		///| Register Handlers/EventManagers |\\\
		GameRegistry.registerWorldGenerator(eventmanager, 0);
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		oreCopper.setHarvestLevel("pickaxe", 1);
		oreTin.setHarvestLevel("pickaxe", 1);
		oreSilver.setHarvestLevel("pickaxe", 2);
		oreLead.setHarvestLevel("pickaxe", 2);
		
		
		///| Recipes |\\\\
		
		/// Crafting \\\
		GameRegistry.addShapelessRecipe(new ItemStack(IGMod.ingotBronze, 3), new Object[]
		{
			IGMod.ingotCopper, IGMod.ingotCopper, IGMod.ingotTin
		});
		
		
		/// Smelting \\\
		GameRegistry.addSmelting(IGMod.oreCopper, new ItemStack(IGMod.ingotCopper), 0.15F);
		GameRegistry.addSmelting(IGMod.oreTin, new ItemStack(IGMod.ingotTin), 0.15F);
		GameRegistry.addSmelting(IGMod.oreSilver, new ItemStack(IGMod.ingotSilver), 0.15F);
		GameRegistry.addSmelting(IGMod.oreLead, new ItemStack(IGMod.ingotLead), 0.15F);
		
		/// OreDictionary \\\
		OreDictionary.registerOre("oreCopper", IGMod.oreCopper);
		OreDictionary.registerOre("oreTin", IGMod.oreTin);
		OreDictionary.registerOre("oreSilver", IGMod.oreSilver);
		OreDictionary.registerOre("oreLead", IGMod.oreLead);
		
		OreDictionary.registerOre("ingotCopper", IGMod.ingotCopper);
		OreDictionary.registerOre("ingotTin", IGMod.ingotTin);
		OreDictionary.registerOre("ingotSilver", IGMod.ingotSilver);
		OreDictionary.registerOre("ingotLead", IGMod.ingotLead);
		OreDictionary.registerOre("ingotBronze", IGMod.ingotBronze);
	}
	
	@Mod.EventHandler
	public void postload(FMLPostInitializationEvent event) {}
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{

	}
	
	private void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
	
	private void registerBlock(Block block, Class itemClass)
	{
		GameRegistry.registerBlock(block, itemClass, block.getUnlocalizedName());
	}
	
	private void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
}

