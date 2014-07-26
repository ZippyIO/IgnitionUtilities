package IgnitionCore.misc;

import IgnitionCore.power.multipart.MessageMicroPartUpdate;
import IgnitionCore.power.multipart.MultipartReference;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class IGLNet_Utils {
	
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("ignitioncore");
	
	public static void initMessages(){
		INSTANCE.registerMessage(MessageUpdate.class, MessageUpdate.class, 0, Side.CLIENT);
		//INSTANCE.registerMessage(MessageClimateStation.class, MessageClimateStation.class, 1, Side.SERVER);
		//INSTANCE.registerMessage(MessageCrafter.class, MessageCrafter.class, 2, Side.SERVER);
		//INSTANCE.registerMessage(MessageMachineMode.class, MessageMachineMode.class, 3, Side.SERVER);
		//INSTANCE.registerMessage(MessagePainter.class, MessagePainter.class, 4, Side.SERVER);
		//INSTANCE.registerMessage(MessageTesseract.class, MessageTesseract.class, 5, Side.SERVER);
		if(MultipartReference.isMicroPartActived)
		INSTANCE.registerMessage(MessageMicroPartUpdate.class, MessageMicroPartUpdate.class, 6, Side.CLIENT);
	}
	
	public static void sendUpdate(TileEntity t){
		if(t == null)return;
//		LogHelper.log(t);
		MessageUpdate m = new MessageUpdate((SyncTile) t);
		INSTANCE.sendToAll(m);
	}	

}
