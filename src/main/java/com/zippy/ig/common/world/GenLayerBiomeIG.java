package com.zippy.ig.common.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zippy.ig.common.IGMod;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;


public class GenLayerBiomeIG extends GenLayerBiome
{
	private BiomeGenBase[] field_151623_c;

	public GenLayerBiomeIG(long par1, GenLayer par3GenLayer, WorldType par4WorldType)
    {
        super(par1, par3GenLayer, par4WorldType);
        this.field_151623_c = new BiomeGenBase[] {IGMod.SacredSpringz};
        this.parent = par3GenLayer;
    }
}