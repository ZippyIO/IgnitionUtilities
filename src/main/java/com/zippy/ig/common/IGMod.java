package com.zippy.ig.common;

import com.zippy.ig.common.block.oreColanite;
import com.zippy.ig.common.block.oreCopper;
import com.zippy.ig.common.block.oreGoldanite;
import com.zippy.ig.common.block.oreIronite;
import com.zippy.ig.common.block.oreLapanite;
import com.zippy.ig.common.block.oreLead;
import com.zippy.ig.common.block.oreRedanite;
import com.zippy.ig.common.block.oreSilver;
import com.zippy.ig.common.block.oreTin;
import com.zippy.ig.common.eventmanager.EventManager;
import com.zippy.ig.common.gui.GuiHandler;
import com.zippy.ig.common.item.ingot.Lapanite;
import com.zippy.ig.common.item.ingot.ingotBronze;
import com.zippy.ig.common.item.ingot.ingotCopper;
import com.zippy.ig.common.item.ingot.ingotLead;
import com.zippy.ig.common.item.ingot.ingotSilver;
import com.zippy.ig.common.item.ingot.ingotTin;
import com.zippy.ig.common.tileentity.TileEntityLeadFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
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
	public static Block oreColanite;
	public static Block oreGoldanite;
	public static Block oreIronite;
	public static Block oreLapanite;
	public static Block oreRedanite;
	
	public static Block leadFurnace;
	public static Block leadFurnaceActive;
	///| Items |\\\
	
	/// Ingots \\\
	public static Item ingotCopper;
	public static Item ingotTin;
	public static Item ingotSilver;
	public static Item ingotLead;
	public static Item ingotBronze;
	public static Item Colanite;
	public static Item Lapanite;
	
	/// Mod Handlers/EventManagers \\\
	EventManager eventmanager = new EventManager();
	public static IGCreativeTab tabCreative = new IGCreativeTab("IgnitionUtil");

	
	@Instance("ignitionutilities")
	public static IGMod instance;
	
	@Mod.EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		///| Blocks |\\\
		
		/// Ores |\\\
		oreCopper = new oreCopper(Material.rock).setHardness(1.5F).setBlockName("oreCopper").setCreativeTab(IGMod.tabCreative).setBlockTextureName("oreCopper");
		oreTin = new oreTin(Material.rock).setHardness(1.5F).setBlockName("oreTin").setCreativeTab(IGMod.tabCreative).setBlockTextureName("oreTin");
		oreSilver = new oreSilver(Material.rock).setHardness(1.5F).setBlockName("oreSilver").setCreativeTab(IGMod.tabCreative).setBlockTextureName("oreSilver");
		oreLead = new oreLead(Material.rock).setHardness(1.5F).setBlockName("oreLead").setCreativeTab(IGMod.tabCreative).setBlockTextureName("oreLead");
		oreColanite = new oreColanite(Material.rock).setBlockName("oreColanite").setBlockTextureName("oreColanite").setCreativeTab(IGMod.tabCreative);
		oreGoldanite = new oreGoldanite(Material.rock).setBlockName("oreGoldanite").setBlockTextureName("oreGoldanite").setHardness(1.5F).setCreativeTab(IGMod.tabCreative);
		oreIronite = new oreIronite(Material.rock).setBlockName("oreIronite").setBlockTextureName("oreIronite").setHardness(1.5F).setCreativeTab(IGMod.tabCreative);
		oreLapanite = new oreLapanite(Material.rock).setBlockName("oreLapanite").setBlockTextureName("oreLapanite").setHardness(1.5F).setCreativeTab(IGMod.tabCreative);
		oreRedanite = new oreRedanite(Material.rock).setBlockName("oreRedanite").setBlockTextureName("oreRedanite").setHardness(1.5F).setCreativeTab(IGMod.tabCreative);
		
		
		leadFurnace = new leadFurnace(false).setBlockName("leadFurnace").setHardness(1.5F).setCreativeTab(IGMod.tabCreative);
		leadFurnaceActive = new leadFurnace(true).setBlockName("leadFurnaceActive").setHardness(1.5F);
		///| Items |\\\
		
		/// Ingots \\\
		ingotCopper = new ingotCopper().setUnlocalizedName("ingotCopper").setTextureName("ingotCopper").setCreativeTab(IGMod.tabCreative);
		ingotTin = new ingotTin().setUnlocalizedName("ingotTin").setTextureName("ingotTin").setCreativeTab(IGMod.tabCreative);
		ingotSilver = new ingotSilver().setUnlocalizedName("ingotSilver").setTextureName("ingotSilver").setCreativeTab(IGMod.tabCreative);
		ingotLead = new ingotLead().setUnlocalizedName("ingotLead").setTextureName("ingotLead").setCreativeTab(IGMod.tabCreative);
		ingotBronze = new ingotBronze().setUnlocalizedName("ingotBronze").setTextureName("ingotBronze").setCreativeTab(IGMod.tabCreative);
		Colanite = new Colanite().setUnlocalizedName("Colanite").setTextureName("Colanite").setCreativeTab(IGMod.tabCreative);
		Lapanite = new Lapanite().setUnlocalizedName("Lapanite").setTextureName("Lapanite").setCreativeTab(IGMod.tabCreative);
		
		///| Register Blocks |\\\
		
		/// Register Ores ///
		registerBlock(oreCopper);
		registerBlock(oreTin);
		registerBlock(oreSilver);
		registerBlock(oreLead);
		registerBlock(oreColanite);
		registerBlock(oreGoldanite);
		registerBlock(oreIronite);
		registerBlock(oreLapanite);
		registerBlock(oreRedanite);
		
		registerBlock(leadFurnace);
		registerBlock(leadFurnaceActive);
		
		///| Register Items |\\\
		
		/// Ingots \\\
		registerItem(ingotCopper);
		registerItem(ingotTin);
		registerItem(ingotSilver);
		registerItem(ingotLead);
		registerItem(ingotBronze);
		registerItem(Colanite);
		registerItem(Lapanite);
		
		///| Register Handlers/EventManagers |\\\
		GameRegistry.registerWorldGenerator(eventmanager, 0);
		GuiHandler GuiHandler = new GuiHandler();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerTileEntity(TileEntityLeadFurnace.class, "TileEntityLeadFurnace");
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		oreCopper.setHarvestLevel("pickaxe", 1);
		oreTin.setHarvestLevel("pickaxe", 1);
		oreSilver.setHarvestLevel("pickaxe", 2);
		oreLead.setHarvestLevel("pickaxe", 2);
		oreIronite.setHarvestLevel("pickaxe", 1);
		oreGoldanite.setHarvestLevel("pickaxe", 2);
		oreLapanite.setHarvestLevel("pickaxe", 2);
		oreRedanite.setHarvestLevel("pickaxe", 2);
		
		///| Recipes |\\\\
		
		/// Crafting \\\
		GameRegistry.addShapelessRecipe(new ItemStack(IGMod.ingotBronze, 3), new Object[]
		{
			IGMod.ingotCopper, IGMod.ingotCopper, IGMod.ingotTin
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.mossy_cobblestone, 2), new Object[]
		{
			Items.clay_ball, Items.wheat, Blocks.cobblestone
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.stonebrick, 2, 1), new Object[]
		{
			Items.clay_ball, Items.wheat, Blocks.stonebrick
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 4), new Object[]
		{
			IGMod.Lapanite
		});
		
		GameRegistry.addRecipe(new ItemStack(IGMod.leadFurnace), new Object[]
		{
			"LIL", "LFL", "LIL", 'L', IGMod.ingotLead, 'I', Items.iron_ingot, 'F', Blocks.furnace
		});
		
		
		/// Smelting \\\
		GameRegistry.addSmelting(IGMod.oreCopper, new ItemStack(IGMod.ingotCopper), 0.15F);
		GameRegistry.addSmelting(IGMod.oreTin, new ItemStack(IGMod.ingotTin), 0.15F);
		GameRegistry.addSmelting(IGMod.oreSilver, new ItemStack(IGMod.ingotSilver), 0.15F);
		GameRegistry.addSmelting(IGMod.oreLead, new ItemStack(IGMod.ingotLead), 0.15F);
		GameRegistry.addSmelting(IGMod.oreColanite, new ItemStack(IGMod.Colanite), 0.15F);
		
		/// OreDictionary \\\
		OreDictionary.registerOre("oreCopper", IGMod.oreCopper);
		OreDictionary.registerOre("oreTin", IGMod.oreTin);
		OreDictionary.registerOre("oreSilver", IGMod.oreSilver);
		OreDictionary.registerOre("oreLead", IGMod.oreLead);
		OreDictionary.registerOre("oreIron", IGMod.oreIronite);
		OreDictionary.registerOre("oreGold", IGMod.oreGoldanite);
		OreDictionary.registerOre("oreRedstone", IGMod.oreRedanite);
		OreDictionary.registerOre("oreLapis", IGMod.oreLapanite);
		
		OreDictionary.registerOre("ingotCopper", IGMod.ingotCopper);
		OreDictionary.registerOre("ingotTin", IGMod.ingotTin);
		OreDictionary.registerOre("ingotSilver", IGMod.ingotSilver);
		OreDictionary.registerOre("ingotLead", IGMod.ingotLead);
		OreDictionary.registerOre("ingotBronze", IGMod.ingotBronze);
		OreDictionary.registerOre("dyeBlue", IGMod.Lapanite);
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

