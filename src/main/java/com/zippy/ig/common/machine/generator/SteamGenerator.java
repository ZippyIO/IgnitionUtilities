package com.zippy.ig.common.machine.generator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import IgnitionCore.misc.IUpdatedEntity;

import com.zippy.ig.common.IGMod;
import com.zippy.ig.common.machine.BlockConductor;
import com.zippy.ig.common.registry.IGRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class SteamGenerator extends BlockConductor {

	protected IIcon blockIcon;
	protected IIcon blockIconFace;
	protected IIcon blockIconFaceActive;
	
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
	
	/*public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[3];
		icons[0] = IR.registerIcon("ignitionutilities:chasis");
		icons[1] = IR.registerIcon("ignitionutilities:machines/generator_off");
		icons[2] = IR.registerIcon("ignitionutilities:machines/generator_on");
	}
	
	public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1)return icons[0];
		if(meta == 0)return icons[1];
		return icons[2];
	}*/
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("ignitionutilities:GeneratorCasing");
		blockIconFaceActive = par1IconRegister.registerIcon("ignitionutilities:GeneratorDisable");
		blockIconFace = par1IconRegister.registerIcon("ignitionutilities:GeneratorActive");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){

		if (meta > 5) {
			return meta == 0 && side == 6 ? blockIconFaceActive : (side + 6 == meta ? blockIconFaceActive : blockIcon);
		} else 
			return meta == 0 && side == 3 ? blockIconFace : (side == meta ? blockIconFace : blockIcon);
	}
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
		{
			int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;

			switch (l)
			{
			case 0:
			world.setBlockMetadataWithNotify(i, j, k, 2, l);
			break;

			case 1:
			world.setBlockMetadataWithNotify(i, j, k, 5, l);
			break;

			case 2:
			world.setBlockMetadataWithNotify(i, j, k, 3, l);
			break;

			case 3:
			world.setBlockMetadataWithNotify(i, j, k, 4, l);
			break;
			}
		}
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

