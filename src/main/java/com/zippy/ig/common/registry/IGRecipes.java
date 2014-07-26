package com.zippy.ig.common.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.zippy.ig.common.IGMod;

import cpw.mods.fml.common.registry.GameRegistry;

public class IGRecipes {

	public static void preInit() {

		
		///| Recipes |\\\\
		
		/// Crafting \\\
		GameRegistry.addShapelessRecipe(new ItemStack(IGItems.ingotBronze, 3), new Object[]
		{
			IGItems.ingotCopper, IGItems.ingotCopper, IGItems.ingotTin
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.bone, 16), new Object[]
		{
			IGItems.Colanite, IGItems.Colanite, IGItems.Colanite
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
			IGItems.Lapanite
		});
		
		GameRegistry.addRecipe(new ItemStack(IGBlocks.leadFurnace), new Object[]
		{
			"LIL", "LFL", "LIL", 'L', IGItems.ingotLead, 'I', Items.iron_ingot, 'F', Blocks.furnace
		});
		
		
		/// Smelting \\\
		GameRegistry.addSmelting(IGBlocks.oreCopper, new ItemStack(IGItems.ingotCopper), 0.15F);
		GameRegistry.addSmelting(IGBlocks.oreTin, new ItemStack(IGItems.ingotTin), 0.15F);
		GameRegistry.addSmelting(IGBlocks.oreSilver, new ItemStack(IGItems.ingotSilver), 0.15F);
		GameRegistry.addSmelting(IGBlocks.oreLead, new ItemStack(IGItems.ingotLead), 0.15F);
		GameRegistry.addSmelting(IGBlocks.oreColanite, new ItemStack(IGItems.Colanite), 0.15F);
		GameRegistry.addSmelting(IGBlocks.oreIronite, new ItemStack(Items.iron_ingot), 0.15F);
		GameRegistry.addSmelting(IGBlocks.oreGoldanite, new ItemStack(Items.gold_ingot), 0.15F);
		
		/// OreDictionary \\\

		

		
	}
}
