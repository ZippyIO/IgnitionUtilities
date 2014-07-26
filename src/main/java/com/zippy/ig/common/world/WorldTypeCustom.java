package com.zippy.ig.common.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraftforge.common.DimensionManager;


public class WorldTypeCustom extends WorldType
{
	public WorldTypeCustom(int i, String string) 
	{
        super("BIOMESOP");
        
        DimensionManager.unregisterProviderType(0);
        DimensionManager.registerProviderType(0, WorldProviderSurfaceIG.class, true);
	}

    @Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer)
    {
        GenLayer ret = new GenLayerBiomeIG(200L, parentLayer, this);

        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }
    
    @Override
	public WorldChunkManager getChunkManager(World world)
    {
    	return new WorldChunkManagerIG(world);
    }
}
