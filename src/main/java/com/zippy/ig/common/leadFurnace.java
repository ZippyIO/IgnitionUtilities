package com.zippy.ig.common;

import java.util.Random;

import com.zippy.ig.common.tileentity.TileEntityLeadFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class leadFurnace extends BlockContainer
{

	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon front;

	private static boolean isBurning;
	private final boolean isBurning2;
	private final Random random = new Random();

	protected leadFurnace(boolean isActive) {
		super(Material.rock);
		isBurning2 = isActive;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister) {
		this.blockIcon = iconregister.registerIcon(IGMod.modid + ":LeadFurnaceSide");
		this.front = iconregister.registerIcon(this.isBurning2 ? IGMod.modid + ":LeadFurnaceActive" : IGMod.modid + ":LeadFurnaceInactive");
		this.top = iconregister.registerIcon(IGMod.modid + ":LeadFurnaceTop");
	}

	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return top;
		} else if (side == 3) {
			return front;
		} else {
			return this.blockIcon;
		}
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		player.openGui(IGMod.instance, 0, world, x, y, z);
		return true;
	}

	public Item getItemDropped(int par1, Random random, int par3) {
		return Item.getItemFromBlock(IGMod.leadFurnace);
	}

	public Item getItem(World world, int par2, int par3, int par4) {
		return Item.getItemFromBlock(IGMod.leadFurnace);
	}
	
	/**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TileEntityLeadFurnace();
    }
    
	
/*	@SideOnly(Side.CLIENT)
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		this.direction(par1World, par2, par3, par4);
	}

	private void direction(World par1World, int par2, int par3, int par4) {
		if (!par1World.isRemote) {
			Block l = par1World.getBlock(par2, par3, par4 - 1);
			Block i1 = par1World.getBlock(par2, par3, par4 + 1);
			Block j1 = par1World.getBlock(par2 - 1, par3, par4);
			Block k1 = par1World.getBlock(par2 + 1, par3, par4);
			byte b0 = 3;

			if (l.func_149730_j() && i1.func_149730_j()) {
				b0 = 3;
			}

			if (i1.func_149730_j() && !l.func_149730_j()) {
				b0 = 2;
			}

			if (j1.func_149730_j() && !k1.func_149730_j()) {
				b0 = 5;
			}

			if (k1.func_149730_j() && !j1.func_149730_j()) {
				b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}

	/*public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
		int direction = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (direction == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (direction == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (direction == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (direction == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

		if (itemstack.hasDisplayName()) {
			((TileEntityLeadFurnace) world.getTileEntity(x, y, z)).furnaceName(itemstack.getDisplayName());
		}
	/*/ //}

   /* public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    	super.onBlockAdded(par1World, par2, par3, par4);
    	this.setDefaultDirection(par1World, par2, par3, par4);
    }
    	
    private void setDefaultDirection(World par1World, int par2, int par3, int par4) {
    	if (!par1World.isRemote) {
    		Block l = par1World.getBlock(par2, par3, par4 - 1);
    		Block i1 = par1World.getBlock(par2, par3, par4 + 1);
    		Block j1 = par1World.getBlock(par2 - 1, par3, par4);
    		Block k1 = par1World.getBlock(par2 + 1, par3, par4);
    		byte b0 = 3;
    		if (l.func_149730_j() && !i1.func_149730_j())
    			b0 = 3;

    		if (i1.func_149730_j() && !l.func_149730_j())
    			b0 = 2;

    		if (j1.func_149730_j() && !k1.func_149730_j())
    			b0 = 5;

    		if (k1.func_149730_j() && !j1.func_149730_j())
    			b0 = 4;

    		par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
    	}
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
    	return (side == world.getBlockMetadata(x, y, z)) ? this.front : this.blockIcon;
    }/*/
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
    	// no need to figure out the right orientation again when the piston block can do it for us
    	int direction = BlockPistonBase.determineOrientation(world, x, y, z, entity);
    	world.setBlockMetadataWithNotify(x, y, z, direction, 2);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
    	return (side == world.getBlockMetadata(x, y, z)) ? this.front : this.blockIcon;
    }

//@SideOnly(Side.CLIENT)
//@Override
//public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
//	return (side == world.getBlockMetadata(x, y, z)) ? this.front : this.blockIcon;
//}
	public static void updateBlockState(boolean burning, World world, int x, int y, int z) {
		int direction = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getTileEntity(x, y, z);
		isBurning = true;

		if (burning) {
			world.setBlock(x, y, z, IGMod.leadFurnaceActive);
		} else {
			world.setBlock(x, y, z, IGMod.leadFurnace);
		}

		isBurning = false;
		world.setBlockMetadataWithNotify(x, y, z, direction, 2);

		if (tileentity != null) {
			tileentity.validate();
			world.setTileEntity(x, y, z, tileentity);
		}
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if (!isBurning) {
			TileEntityLeadFurnace tileentityleadfurnace = (TileEntityLeadFurnace) world.getTileEntity(x, y, z);

			if (tileentityleadfurnace != null) {
				for (int i = 0; i < tileentityleadfurnace.getSizeInventory(); ++i) {
					ItemStack itemstack = tileentityleadfurnace.getStackInSlot(i);

					if (itemstack != null) {
						float f = this.random.nextFloat() * 0.6F + 0.1F;
						float f1 = this.random.nextFloat() * 0.6F + 0.1F;
						float f2 = this.random.nextFloat() * 0.6F + 0.1F;

						while (itemstack.stackSize > 0) {
							int j = this.random.nextInt(21) + 10;

							if (j > itemstack.stackSize) {
								j = itemstack.stackSize;
							}

							itemstack.stackSize -= j;
							EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

							if (itemstack.hasTagCompound()) {
								entityitem.getEntityItem().setTagCompound(((NBTTagCompound) itemstack.getTagCompound().copy()));
							}

							float f3 = 0.025F;
							entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
							entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.1F);
							entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);
							world.spawnEntityInWorld(entityitem);
						}
					}
				}
				world.func_147453_f(x, y, z, block);
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (this.isBurning2) {
			int direction = world.getBlockMetadata(x, y, z);

			float xx = (float) x + 0.5F, yy = (float) y + random.nextFloat() * 6.0F / 16.0F, zz = (float) z + 0.5F, xx2 = random.nextFloat() * 0.3F - 0.2F, zz2 = 0.5F;

			if (direction == 4) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			} else if (direction == 5) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			} else if (direction == 3) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			} else if (direction == 2) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			}
		}
	}

}

