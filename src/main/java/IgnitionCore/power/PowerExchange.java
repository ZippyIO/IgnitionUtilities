package IgnitionCore.power;

public class PowerExchange {
	
	public static final int QFtRF = 5;//RF => MeV

	public static float FTtoHeat(float ft){
		return ft/10;
	}
	
	public static float HeattoFT(float h){
		return h*10;
	}

	public static float HeatPerFluid(int f) {
		//1000mb <= 20C
		return 0.02f*f;
	}
	
	public static float FluidPerHeat(int h) {
		//1000mb => 20C
		return h*50;
	}

	public int MeVtoRF(double cap) {
		return (int) (cap*QFtRF);
	}

	public double RFtoMev(int i) {
		return i/QFtRF;
	}

	public double FTtoMev(float progres) {
		return progres/2;
	}
}
