package IgnitionCore.power;

public interface IPower {
	
	public static final String ELEC_NAME = " QF";
	
	double addCharge(double amount);
	
	void removeCharge(double amount);
	
	double getCharge();
	
	double getCapacity();
	
	double getFlow();
}
