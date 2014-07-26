package IgnitionCore;

import java.util.Random;

import IgnitionCore.misc.IGLNet_Utils;
import IgnitionCore.power.multipart.MicroRegistry;
import IgnitionCore.power.multipart.MultipartReference;
import IgnitionCore.proxy.IGLCommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
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
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = IgnitionLib.modid, name = "IgnitionLib", version = "1.0b-MC1.7.2", dependencies = "")
public class IgnitionLib
{
	/// Icons/Textures are temporary \\\
	
	
	/// Class files for each part of this made will be done in a latter commit, 
	/// Items & Blocks will be split into other class files along with other things for a clean main class file.
	public static final String modid = "IgnitionLib";
	
	@SidedProxy(clientSide = "IgnitionCore.proxy.IGLClientProxy", serverSide = "IgnitionCore.proxy.IGLCommonProxy")
	public static IGLCommonProxy proxy;
	
	@Instance("IgnitionLib")
	public static IgnitionLib instance;
	
	@Mod.EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		
		IGLNet_Utils.initMessages();
		

		
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
		if(MultipartReference.isMicroPartActived) new MicroRegistry().load();
	}
	
	@Mod.EventHandler
	public void postload(FMLPostInitializationEvent event) {
		
		
		
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

