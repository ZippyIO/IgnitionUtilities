package IgnitionCore.power.multipart;

import IgnitionCore.ItemBigCableMultipart;
import IgnitionCore.ItemPlaneCableMultipart;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import cpw.mods.fml.common.registry.GameRegistry;

public class MicroRegistry{

	//micro
	public static Item BasicCable;
	public static Item FluxCable;

	public void load(){
		MicroRegistry.BasicCable = new ItemPlaneCableMultipart();
		GameRegistry.registerItem(BasicCable, "IG_Cable_Basic");
		MultiPartRegistry.registerParts(new PlaneCable(), new String[]{MicroRegistry.BasicCable.getUnlocalizedName()});
		MinecraftForgeClient.registerItemRenderer(BasicCable, new RenderCablePlaneItem());
		
		MicroRegistry.FluxCable = new ItemBigCableMultipart();
		GameRegistry.registerItem(FluxCable, "IG_Cable_Flux");
		MultiPartRegistry.registerParts(new BigCable(), new String[]{MicroRegistry.FluxCable.getUnlocalizedName()});
		MinecraftForgeClient.registerItemRenderer(FluxCable, new RenderCableBigItem());
	}
	
	
	public class PlaneCable implements IPartFactory{
		@Override
		public TMultiPart createPart(String arg0, boolean arg1) {
			return new MicroCablePlane();
		}		
	}
	
	public class BigCable implements IPartFactory{
		@Override
		public TMultiPart createPart(String arg0, boolean arg1) {
			return new MicroCableBig();
		}		
	}
}
