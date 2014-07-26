package IgnitionCore.power;

import java.util.ArrayList;
import java.util.List;

import IgnitionCore.misc.IGL_Utils;
import IgnitionCore.power.multipart.MicroPartUtil;
import IgnitionCore.power.multipart.MultipartReference;
import net.minecraft.tileentity.TileEntity;

public class PowerNetwork {

	private List<PowerInterface> interfaces = new ArrayList<PowerInterface>();
	private List<PowerInterface> excluded = new ArrayList<PowerInterface>();
	private List<PowerPath> rutes = new ArrayList<PowerPath>();

	private PowerNetwork(){}
	public static PowerNetwork create(PowerInterface base){
		PowerNetwork p = new PowerNetwork();
		p.interfaces.add(base);
		return p;
	}

	public List<PowerInterface> getConductors(){
		return interfaces;
	}

	public List<StorageInterface> getMachines(){
		List<StorageInterface> s = new ArrayList<StorageInterface>();
		for(PowerInterface p : interfaces)if(p instanceof StorageInterface)s.add((StorageInterface) p);
		return s;
	}

	public void refresh(){

		IPowerConductor base = null;
		for(PowerInterface e : interfaces){
			TileEntity t = e.getParent();
			if(t.getWorldObj().getTileEntity(t.xCoord, t.yCoord, t.zCoord) instanceof IPowerConductor){
				base = (IPowerConductor) t;
			}
		}

		if(base != null){
			List<IPowerConductor> things = new ArrayList<IPowerConductor>();
			things.addAll(new PowerPathfinder(base, null).getFinding());
			List<PowerInterface> inters = new ArrayList<PowerInterface>();
			for(IPowerConductor p : things) inters.add(p.getPower());
			interfaces = inters;
			excluded.clear();
		}

		for(PowerInterface e : interfaces){
			e.setNetwork(this);
			e.onNetworkUpdate();
		}
	}

	public void onNetworkUpdate(){
		rutes.clear();
	}

	public void mergeWith(PowerNetwork net){
		for(PowerInterface te : interfaces){
			te.setNetwork(net);
		}
		for(PowerInterface e : interfaces){
			e.onNetworkUpdate();
		}
	}
	public boolean canPowerGoToEnd(IPowerConductor from, IPowerConductor to) {
		for(PowerPath p:rutes){
			if(p.contains(from,to))return true;
		}
		if((new PowerPathfinder(from, to)).canGoToTheEnd()){
			rutes.add(new PowerPath(from,to));
			return true;
		}else return false;
	}

	public void excludeAndRecalculate(IPowerConductor p) {
		try{
			if(MultipartReference.isMicroPartActived){
				MicroPartUtil.excludeAndRecalculate(p);
			}else{
				for(TileEntity t : IGL_Utils.getTiles(p.getPower().getParent())){
					if(t instanceof IPowerConductor){
						((IPowerConductor) t).getPower().getNetwork().refresh();
					}
				}
			}
		}catch(Exception e){}

	}

}

