package IgnitionCore;

import IgnitionCore.power.multipart.MicroCablePlane;

import com.zippy.ig.common.registry.IGRegistry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.JItemMultiPart;
import codechicken.multipart.TMultiPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPlaneCableMultipart extends JItemMultiPart {
    
    public ItemPlaneCableMultipart() {
        super();
        setUnlocalizedName("IG_Basic_cable");
        setCreativeTab(IGRegistry.tabCreative);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("ignitionutilities:cable");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World w, int x, int y, int z, int side, float f, float f2, float f3) {
    
        if (super.onItemUse(stack, player, w, x, y, z, side, f, f2, f3)) {
            w.playSoundEffect(x + 0.5, y + 0.5, z + 0.5,
                    Block.soundTypeMetal.getBreakSound(),
                    Block.soundTypeMetal.getVolume() * 5.0F,
                    Block.soundTypeMetal.getPitch() * .9F);
            return true;
        }
        return false;
    }
    
	@Override
	public TMultiPart newPart(ItemStack arg0, EntityPlayer arg1, World arg2,
			BlockCoord arg3, int arg4, Vector3 arg5) {
		return new MicroCablePlane();
	}
    
}
