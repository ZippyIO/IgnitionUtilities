package IgnitionCore.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class PowerUtils {

	public static void MoveCharge(IPowerConductor a, IPowerConductor b){
		if(a == null || b == null)return;
		StorageInterface from = (StorageInterface) a.getPower();
		StorageInterface to = (StorageInterface) b.getPower();
		if(from == null || to == null)return;
		if(from.equals(to))return;
		if(from.getCharge() > 0){
			double space = to.getCapacity()-to.getCharge();
			double flow = Math.min(from.getFlow(), to.getFlow());
			if(space > 0){
				if(from.getNetwork().canPowerGoToEnd(a,b)){
					if(from.getCharge() > flow && space > flow){
						from.removeCharge(flow);
						to.addCharge(flow);
					}else if(from.getCharge() >= space){
						from.removeCharge(space);
						to.addCharge(space);
					}else{
						to.addCharge(from.getCharge());
						from.removeCharge(from.getCharge());
					}
				}
			}
		}
	}

	public static TileEntity getRelative(TileEntity from, ForgeDirection d){
		return from.getWorldObj().getTileEntity(from.xCoord + d.offsetX, from.yCoord + d.offsetY, from.zCoord + d.offsetZ);
	}

	public static boolean canConnectTo(TileEntity a, TileEntity b) {
		if(a!=null && b!=null){
			if(a instanceof IPowerConductor && b instanceof IPowerConductor){
				return true;
			}
		}
		return false;
	}
}

