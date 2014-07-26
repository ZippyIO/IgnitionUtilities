package com.zippy.ig.common;

import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;

import com.zippy.ig.common.biome.BiomeGenSacredSpringz;
import com.zippy.ig.common.eventmanager.EventManager;
import com.zippy.ig.common.gui.GuiHandler;
import com.zippy.ig.common.misc.TiCReference;
import com.zippy.ig.common.registry.IGBlocks;
import com.zippy.ig.common.registry.IGItems;
import com.zippy.ig.common.registry.IGRecipes;
import com.zippy.ig.common.registry.IGRegistry;
import com.zippy.ig.common.registry.IGTiC;
import com.zippy.ig.common.tile.PulverizierTEMessage;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = IGMod.modid, name = "IgnitionUtilities Mod", version = "0.3-MC1.7.2", dependencies = "required-after:IgnitionLib")
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

	
	///| Items |\\\
	
	
	
	/// Ingots \\\

	

	
	public static final BiomeGenBase SacredSpringz = (new BiomeGenSacredSpringz(40)).setColor(39259).setBiomeName("SacredSpringz").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F);
	
	public static SimpleNetworkWrapper snw; 
	
	/// Mod Handlers/EventManagers \\\
	

	EventManager eventmanager = new EventManager();
	@Instance("ignitionutilities")
	public static IGMod instance;
	
	@Mod.EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		IGItems.preInit();
		IGRecipes.preInit();
		IGBlocks.preInit();
		IGRegistry.preInit();
		

		
		snw = NetworkRegistry.INSTANCE.newSimpleChannel("ignitionutilities"); 
		snw.registerMessage(PulverizierTEMessage.class, PulverizierTEMessage.class, 1, Side.CLIENT);
		GameRegistry.registerWorldGenerator(eventmanager, 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
	}
	
	@Mod.EventHandler
	public void postload(FMLPostInitializationEvent event) {
		
		if(TiCReference.TiCAvailable) new IGTiC().preInit();
		
	}
	
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{

	}
	

	
	private void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
	public static void registerEntity(Class entityClass, String name)
	{
	int entityID = EntityRegistry.findGlobalUniqueEntityId();
	long seed = name.hashCode();
	Random rand = new Random(seed);
	int primaryColor = rand.nextInt() * 16777215;
	int secondaryColor = rand.nextInt() * 16777215;

	EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
	EntityRegistry.registerModEntity(entityClass, name, entityID, instance, 64, 1, true);
	EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
	}
}

