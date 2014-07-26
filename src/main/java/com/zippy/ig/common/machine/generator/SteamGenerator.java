package com.zippy.ig.common.machine.generator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import IgnitionCore.misc.IUpdatedEntity;

import com.zippy.ig.common.IGMod;
import com.zippy.ig.common.machine.BlockConductor;
import com.zippy.ig.common.registry.IGRegistry;


public class SteamGenerator extends BlockConductor {

	public IIcon[] icons;
	
	public SteamGenerator(Material m) {
		super(m);
		setCreativeTab(IGRegistry.tabCreative);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("steamgenerator");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileSteamGenerator();
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[3];
		icons[0] = IR.registerIcon("ignitionutilities:chasis");
		icons[1] = IR.registerIcon("ignitionutilities:machines/generator_off");
		icons[2] = IR.registerIcon("ignitionutilities:machines/generator_on");
	}
	
	public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1)return icons[0];
		if(meta == 0)return icons[1];
		return icons[2];
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(!p.isSneaking())p.openGui(IGMod.instance, 6, world, x, y, z);
		return true;
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, Block block){
		IUpdatedEntity t = (IUpdatedEntity) w.getTileEntity(x, y, z);
		t.onNeigUpdate();
	}
}

